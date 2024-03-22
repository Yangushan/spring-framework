package org.yangushan.aspect;

import org.springframework.cglib.proxy.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;

/**
 * 1. 测试cglib
 * created by yangushan
 * 2024/3/22 10:36
 */
public class TestEnhancer {

	public static void main(String[] args) {

		// 目标对象
		UserService target = new UserService();

		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(UserService.class);
		enhancer.setCallbacks(new Callback[]{
				new MethodInterceptor() {
					/**
					 * @param o 代理对象
					 * @param method 代理的方法
					 * @param objects 参数
					 * @param methodProxy 被代理的方法
					 * @return
					 * @throws Throwable
					 */
					@Override
					public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
						System.out.println("before");
						// 被代理的方法调用目标对象
//				Object invoke = methodProxy.invoke(target, objects);
						// java反射也可以
//				Object invoke = method.invoke(target, objects);
						// 递归调用，因为o是代理对象，报错
//				Object invoke = methodProxy.invoke(o, objects);
						// 同理递归调用，报错
//				Object invoke = method.invoke(o, objects);
						// 调用类的父类的方法，因为我们的o是代理对象，所以它的父类是我们的target对象，所以这里的意思就是执行target的test方法，也是行得通的
						Object invoke = methodProxy.invokeSuper(o, objects);
						System.out.println("after");
						return invoke;
					}
				}
		, NoOp.INSTANCE});
		// 这个的意思是什么呢？也就是如果我们设置callbackFilter，返回的数字，他会根据上面我们的callback设置的数组的下标去找位置
		// 如果我们上面的callback数组只有1个，那么如果我们返回超过0的下标，比如返回超过1这就会数组越界
		// 所以这里的数字是不能随便返回的，我们在上面设置了两个callback，第一个是我们自定义的，第二个表示是没有任何操作的意思
		enhancer.setCallbackFilter(new CallbackFilter() {
			@Override
			public int accept(Method method) {
				if (method.getName().equals("test")) {
					return 0;
				}
				return 1;
			}
		});

		// 被代理的对象
		UserService userService = (UserService) enhancer.create();
		userService.test();
		userService.a(); // aaa不会经过我们的intercept因为它走了第二个无操作的代理拦截

	}

}
