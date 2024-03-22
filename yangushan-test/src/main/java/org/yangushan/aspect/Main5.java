package org.yangushan.aspect;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * created by yangushan
 * 2024/3/22 12:06
 */
public class Main5 {

	public static void main(String[] args) {

		// 3. 前面学习了ProxyFactory的用户，spring中提供了一个ProxyFactoryBean也是类似帮我们做一个代理
		// 在AspectAppConfig中定义了ProxyFactoryBean的用户
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectAppConfig.class);
		UserService userService = (UserService) context.getBean("userService");
		userService.test();

		// 4. 上面的业务在使用的过程中一次只能代理一个对象，那么有什么方式可以一次性帮我们代理好多个对象？
		// Spring还提供了一种方式，通过beanName的方式帮我们找到我们需要的bean，帮我们代理的方法，也不需要我们自己去创建对象的代理模式
		// 所以在AspectAppConfig中注入了新的BeanNameAutoProxyCreator，是一个BeanPostProcessor

		// 5. 上面的业务缺点也很明显，必须指定BeanName
		// 前面我们在使用ProxyFactory的过程中用到了advisor那么有没有什么是可以使用到这个逻辑的
		// 这里就有了更高级的一个Bean，也就是DefaultAdvisorAutoProxyCreator，也是一个BeanPostProcessor
		// 这里我们针对test和b设置了两个不同的advisor，所以调用的时候有不同的逻辑
		userService.b();

		// 上面的业务已经完成了我们基本的需求，但是我们平时使用的@Aspectj是怎么实现的？
		// 可以想到我们的DefaultAdvisorAutoProxyCreator只会处理advisor的bean，并不会去出来我们@Aspectj的bean
		// 所以@Aspectj也是肯定有一个类似DefaultAdvisorAutoProxyCreator都BeanPostProcessor，然后去找到所有@Aspectj的bean
		// 最后把这个类里面所有的方法和注解，给我们解析成不同的advisor来完成我们的流程
		// 那么这里就使用到了我们的注解@EnableAspectJAutoProxy，这里面会导入这个BeanPostProcessor
		userService.e();

		// 测试如何在aspectj中拿到方法的参数数据
		userService.f("wo", "ni");

	}

}
