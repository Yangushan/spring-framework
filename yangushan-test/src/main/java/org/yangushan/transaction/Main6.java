package org.yangushan.transaction;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * created by yangushan
 * 2024/3/22 18:19
 */
public class Main6 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TransAppConfig.class);


		UserService userService = (UserService) context.getBean("userService");
		userService.test();
	}

}
