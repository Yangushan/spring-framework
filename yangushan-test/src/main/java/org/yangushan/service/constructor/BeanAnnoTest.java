package org.yangushan.service.constructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.yangushan.service.Student;

/**
 * created by yangushan
 * 2024/3/20 14:58
 */
@Component
@Configuration
public class BeanAnnoTest {

	/**
	 * 这个方法spring在构造BeanDefinition的时候，factoryBeanName就会是beanAnnoTest
	 * @return
	 */
	@Bean
	public BeanAnnoTest1 beanAnnoTest1() {
		System.out.println("BeanAnnoTest: 1");
		return new BeanAnnoTest1();
	}

	/**
	 * 如果保留1和5，谁先被注入就使用谁，另外一个判断到一个是静态一个不是静态不一样，直接忽略，不会再注入
	 * @return
	 */
	@Bean
	public static BeanAnnoTest1 beanAnnoTest1(Student student) {
		System.out.println("BeanAnnoTest: 5");
		return new BeanAnnoTest1();
	}

	/**
	 * 这个方法spring在构造BeanDefinition的时候，因为是静态的，所以会设置factoryClass=BeanAnnoTest.class
	 * 因为这里名字是2没有重复的，所以会被单独认为是一个bean存起来会和1共存
	 * @return
	 */
	@Bean
	public static BeanAnnoTest1 beanAnnoTest2() {
		System.out.println("BeanAnnoTest: 3");
		return new BeanAnnoTest1();
	}

	/**
	 * 如果保留了1和2，因为两个方法名一样的bean，那么spring在使用@Bean构造bean的时候会判断
	 * 因为不是唯一的，所以会走和推断构造方法差不多的流程，先按照参数排序，所以打印出来2
	 * @param student
	 * @return
	 */
//	@Bean
//	public BeanAnnoTest1 beanAnnoTest1(Student student) {
//		System.out.println("BeanAnnoTest: 2");
//		return new BeanAnnoTest1();
//	}


}
