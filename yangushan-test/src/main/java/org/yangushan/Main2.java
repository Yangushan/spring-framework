package org.yangushan;

import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.yangushan.service.*;
import org.yangushan.service.constructor.*;
import org.yangushan.service.qualifier.LoadBalanceTestBean;

public class Main2 {

	public static void main(String[] args) {

		// 测试注入的问题，由于比较特殊，所以注入方式不太一样
		AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext();
		context1.register(AppConfig.class);

		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(C6.class);
		beanDefinition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_CONSTRUCTOR);
		context1.registerBeanDefinition("c6", beanDefinition);

		AbstractBeanDefinition beanDefinition7 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition7.setBeanClass(C7.class);
		// 这里不仅仅可以直接指定我们的bean对象，还可以设置其他的类型
		// org.springframework.beans.factory.support.BeanDefinitionValueResolver.resolveValueIfNecessary，可以参考这段代码中的判断
//		beanDefinition7.getConstructorArgumentValues().addGenericArgumentValue(new OrderService());
		// 这里也可以直接指定具体的某个index的bean对象是多少，但是我们没有这个超过4个参数个数的符合的构造方法，所以会报错
//		beanDefinition7.getConstructorArgumentValues().addIndexedArgumentValue(3, new Student());
		context1.registerBeanDefinition("c7", beanDefinition7);
		context1.refresh();

		// 因为我们在lookupTest这个bean中使用了@Lookup注解，所以这里返回的是它的代理对象
		LookupTest lookupTest = (LookupTest) context1.getBean("lookupTest");
		System.out.println(lookupTest);
		lookupTest.test();

		//测试ReplaceTest
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//		ReplacedTest replacedTest = (ReplacedTest) context.getBean("replaceTest");
//		replacedTest.test();

	}

}