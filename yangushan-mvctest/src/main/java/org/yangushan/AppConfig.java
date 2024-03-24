package org.yangushan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yangushan.interceptor.Test1Interceptor;
import org.yangushan.interceptor.TestInterceptor;

/**
 * created by yangushan
 * 2024/3/24 11:43
 */
@ComponentScan("org.yangushan")
@EnableWebMvc // 这个注解
public class AppConfig implements WebMvcConfigurer {

	/**
	 * 注册拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TestInterceptor());
//		registry.addInterceptor(new Test1Interceptor());
	}
}
