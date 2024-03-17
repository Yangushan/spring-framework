package org.yangushan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yangushan.service.UserService;

public class Main {

	public static void main(String[] args) {


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

		UserService userService = (UserService) context.getBean("userService");

		userService.test();

		// userFactoryBean真正的那个对象，也就是getObject得到的对象
		System.out.println(context.getBean("userFactoryBean"));
		// 得到FactoryBean自己的bean对象
		System.out.println(context.getBean("&userFactoryBean"));

	}

}