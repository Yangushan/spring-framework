package org.yangushan;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
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

		// 这里的测试主要是针对在代码
		// DefaultListableBeanFactory.doResolveDependency
		// 中，代码的开始有一段缓存的原理，为什么要缓存一个bean的依赖注入，就是为了原型考虑
		// 这样第二次原型又get的时候，又要进行依赖注入，就不需要找了，可以直接找到对应符合条件的beanName进行getBean注入
		((Person1)(context.getBean("person1"))).test();
		((Person1)(context.getBean("person1"))).test();

		// 测试@Resource注入流程的类
		((ResourceTestBean)context.getBean("resourceTestBean")).test();

		// 测试原型bean之间的依赖注入，会报错
		System.out.println(context.getBean("a3"));



		// 测试@lazy的循环依赖注入之中导致的问题
		// 我们可以控制让线程1走到getSingleton设置完二级缓存那一步骤
		// 然后这个时候控制线程2去getBean则会因为getSingleton提前返回了不完整的bean对象导致问题
		// 因为这个时候还是一个半成品对象，所以很可能里面的什么属性赋值都没有完成
		// 如果这个时候我们调用了一个没有完成的属性去toString则会导致空指针
		// 这不止这一个问题，很可能还会导致我们其他的问题
		new Thread(() -> {
			((A4)context.getBean("a4")).test();
        }).start();
		new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			((A4)context.getBean("a4")).test();
		}).start();
	}

}