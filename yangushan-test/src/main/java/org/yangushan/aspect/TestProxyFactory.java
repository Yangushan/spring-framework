package org.yangushan.aspect;

import org.aopalliance.aop.Advice;
import org.springframework.aop.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.yangushan.aspect.advice.TestAroundAdvice;
import org.yangushan.aspect.advice.TestBeforeAdvice;
import org.yangushan.aspect.advice.TestThrowAdvice;
import org.yangushan.aspect.advice.TwoAdvice;

import java.lang.reflect.Method;

/**
 * 2. 使用Spring提供的代理工厂类测试
 * created by yangushan
 * 2024/3/22 11:29
 */
public class TestProxyFactory {

	public static void main(String[] args) {

		UserService target = new UserService();
		System.out.println(target);


		ProxyFactory proxyFactory = new ProxyFactory();
		proxyFactory.setTarget(target);

		// 2.0 target和targetSource的区别
		// target是只能设置一个目标，但是targetSource我们可以根据不同的流程返回不同的对象
//		proxyFactory.setTargetSource(new TargetSource() {
//
//			// 比如我们创建一个targetSource，根据不同的功能返回不同的数据，有点像MapperFactoryBean一样的逻辑
//
//			@Override
//			public Class<?> getTargetClass() {
//				return null;
//			}
//
//			@Override
//			public boolean isStatic() {
//				return false;
//			}
//
//			@Override
//			public Object getTarget() throws Exception {
//				return null;
//			}
//
//			@Override
//			public void releaseTarget(Object target) throws Exception {
//
//			}
//		});


		// 2.1 使用原始的advice
		// advice 测试用例
//		proxyFactory.addAdvice(new TestBeforeAdvice());
//		proxyFactory.addAdvice(new TestAroundAdvice());
//		proxyFactory.addAdvice(new TestThrowAdvice()); // 测试异常流程

		// 2.2 使用升级的advisor
		// PointcutAdvisor = Pointcut + advice
		proxyFactory.addAdvisor(new PointcutAdvisor() {
			@Override
			public Pointcut getPointcut() {
				return new Pointcut() {
					@Override
					public ClassFilter getClassFilter() {
						return new ClassFilter() {
							@Override
							public boolean matches(Class<?> clazz) {
								return clazz.equals(UserService.class);
							}
						};
					}

					@Override
					public MethodMatcher getMethodMatcher() {
						return new MethodMatcher() {
							@Override
							public boolean matches(Method method, Class<?> targetClass) {
								return method.getName().equals("test") || method.getName().equals("d");
							}

							@Override
							public boolean isRuntime() {
								return true;
							}

							/**
							 * 这个方法只有在isRuntime=true的时候才会被调用，这里为了测试把默认的isRuntime=false改成了True
							 * @param method the candidate method
							 * @param targetClass the target class
							 * @param args arguments to the method
							 * @return
							 */
							@Override
							public boolean matches(Method method, Class<?> targetClass, Object... args) {
								// 这里我对methodName做一个判断
								if (method.getName().equals("test")) {
									return true;
								}
								if (method.getName().equals("d")) {
									if (args[0].toString().equals("1")) {
										return true;
									}
								}
								return false;
							}
						};
					}
				};
			}

			@Override
			public Advice getAdvice() {
				return new TwoAdvice();
			}

			/**
			 * 这个方法目前没什么作用
			 * @return
			 */
			@Override
			public boolean isPerInstance() {
				return false;
			}
		});


		proxyFactory.setExposeProxy(true); // 暴露被代理对象
		UserService userService = (UserService) proxyFactory.getProxy();
//		userService.a(); // 测试异常流程
		userService.test();
//		userService.b();
//		userService.c();
		userService.d("1"); // 会被注入
		userService.d("2"); // 不会注入，只会打印自己的内容


	}


}
