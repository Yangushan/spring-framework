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

	}

}