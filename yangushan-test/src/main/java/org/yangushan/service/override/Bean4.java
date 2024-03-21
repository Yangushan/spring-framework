package org.yangushan.service.override;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/21 15:57
 */
// 如果这里proxyBeanMethods=false，那么我们就不会生成Bean4的代理类，所以每次调用bean3的方法就会真正调用一次方法
	// 但是如果是true,那么spring就会帮我们生成一个代理类，那么这个代理类在执行@Bean方法的时候就会帮我们经过org.springframework.context.annotation.ConfigurationClassEnhancer#CALLBACKS的拦截
	// 这样就会判断，所以会打印两次就会是true
//@Configuration(proxyBeanMethods = false)
@Configuration(proxyBeanMethods = true)
public class Bean4 {

	@Bean
	public Bean3 bean3() {
		return new Bean3();
	}

	@Bean
	public Bean2 Bean2() {
		System.out.println(bean3());
		System.out.println(bean3());
		return new Bean2();
	}

}
