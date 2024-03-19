package org.yangushan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yangushan.service.*;

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

		// 这里的user是我们通过TestInstantiationAwareBeanPostProcessor创建的，在TestInstantiationAwareBeanPostProcessor中
		// 我们创建的user设置了名字为“TestInstantiationAwareBeanPostProcessor”，所以这里打印getName有数据
		User user = (User) context.getBean("user");
		System.out.println(user.getName());

		Student st = (Student) context.getBean("student");
		st.test();

		// 为了测试bean属性里面的autowired流程
		Person person = (Person) context.getBean("person");
		person.test();

		// 测试复杂bean依赖注入的流程
		MultiBean multiBean = (MultiBean) context.getBean("multiBean");
		multiBean.test();

		// 测试自己注入自己的流程
		SelfBeanSuper selfBeanSuper = (SelfBeanSuper) context.getBean("selfBeanSuper");
		selfBeanSuper.test();

	}

}