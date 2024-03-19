package org.yangushan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.yangushan.service.*;
import org.yangushan.service.qualifier.LoadBalanceTestBean;

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


		// 下面的java API可以打印出我们子类中具体指定的父类的泛型具体指定的是什么
		/**
		 * 可以看出来GenericTypeAwareAutowireCandidateResolver这个解析器在解析哪些bean可以注入的时候，使用了java这种特性去分析了应该使用哪个类来解析
		 * 所以我们这里的test方法打印出了各自子类指定的泛型类型
		 */
		// org.yangushan.service.GenericSuper<org.yangushan.service.Student, org.yangushan.service.Person>
		GenericChild1 genericChild1 = (GenericChild1) context.getBean("genericChild1");
		System.out.println(genericChild1.getClass().getGenericSuperclass().getTypeName());
		genericChild1.test();
		GenericChild2 genericChild2 = (GenericChild2) context.getBean("genericChild2");
		//org.yangushan.service.GenericSuper<org.yangushan.service.UserService, org.yangushan.service.OrderService>
		System.out.println(genericChild2.getClass().getGenericSuperclass().getTypeName());
		genericChild2.test();


		// 这里对Qualifier注解的一种测试
		LoadBalanceTestBean loadBalanceTestBean = (LoadBalanceTestBean) context.getBean("loadBalanceTestBean");
		loadBalanceTestBean.test();

	}

}