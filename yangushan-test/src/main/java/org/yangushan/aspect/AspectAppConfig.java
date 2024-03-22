package org.yangushan.aspect;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.yangushan.aspect.advice.TestAroundAdvice;
import org.yangushan.aspect.advice.TestBeforeAdvice;

/**
 * created by yangushan
 * 2024/3/22 10:37
 */
@ComponentScan("org.yangushan.aspect")
@EnableAspectJAutoProxy // 因为这个注解也可能帮我们识别系统中所有的advisor的bean，不仅可以解析@AspectJ，所以可以把下面的defaultAdvisorAutoProxyCreator注释了
public class AspectAppConfig {

	/**
	 * FactoryBean的模式注入，和我们使用的ProxyFactory的方法有点类似，但是还不够优雅
	 * 这种方式一次只能帮一个bean代理
	 * @return
	 */
//	@Bean
	public ProxyFactoryBean userService() {
		// 使用方法和我们的ProxyFactory一样，设置target，设置advice或者advisor
		UserService userService = new UserService();
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(userService);
		proxyFactoryBean.addAdvice(new TestBeforeAdvice());
		return proxyFactoryBean;
	}

	/**
	 * 是一个BeanPostProcessor的bean，主要是通过实例化前的方法，判断beanName是否和我们的匹配规则一致，如果一致的话则创建代理对象进行返回
	 * 但是这个方法缺点也很明显只能设置beanName，我们可能有bean里面某些方法需要代理，另外方法不需要代理的逻辑，前面有用到ProxyFactory的advisor就很符合我们的需求
	 * @return
	 */
//	@Bean
	public BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
		BeanNameAutoProxyCreator creator = new BeanNameAutoProxyCreator();
		// 这里就可以设置好beanName，重点是可以根据里面的东西进行简单的符号匹配
		creator.setBeanNames("user*");
		// 匹配全部
//		creator.setBeanNames("*");
		// 指定我们的拦截器，也是设置的beanName，所以这里需要把我们的拦截器也设置为一个bean，而不是一个普通类了，这里是和上面的区分
		creator.setInterceptorNames("beforeBeanAdvice");
		return creator;
	}

	/**
	 * 所以这里引出了这个，可以对我们系统中所有的advisor的bean作出一个代理的功能
	 * 也是使用的BeanPostProcessor规则，它主要是通过每个bean在初始化之后，进行代理的一种实现方式，通过判断这个bean是否符合我们所有的advisor，然后来判断是否需要进行一个aop动态代理
	 * @return
	 */
//	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		return creator;
	}
	/**
	 * 由于上面的流程需要系统中存在一些advisor，所以这里创建两个advisor来配合
	 */
	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor1() {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		// 针对test方法
		pointcut.addMethodName("test");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setPointcut(pointcut);
		advisor.setAdvice(new TestBeforeAdvice());
		return advisor;
	}
	/**
	 * 由于上面的流程需要系统中存在一些advisor，所以这里创建两个advisor来配合
	 */
	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor2() {
		NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
		// 针对b方法
		pointcut.addMethodName("b");
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setPointcut(pointcut);
		advisor.setAdvice(new TestAroundAdvice());
		return advisor;
	}


}
