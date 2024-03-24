package org.yangushan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

/**
 * 这个类可以替代我们web.xml+spring.xml的方式来构建我们的dispatcherServlet
 * 但是这样的方式我们添加拦截器可能就需要有其他的方式，可以看appConfig的配置
 * created by yangushan
 * 2024/3/24 11:40
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

	/**
	 * @see SpringServletContainerInitializer#onStartup(Set, ServletContext)
	 * @param servletContext the {@code ServletContext} to initialize
	 * @throws ServletException
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.register(AppConfig.class);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
		ServletRegistration.Dynamic registration = servletContext.addServlet("mvctest", dispatcherServlet);
		registration.setLoadOnStartup(1);
		registration.addMapping("/mvctest/*");

	}
}
