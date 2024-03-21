package org.yangushan;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.yangushan.service.constructor.C6;
import org.yangushan.service.constructor.C7;
import org.yangushan.service.constructor.LookupTest;

public class Main3 {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext();
		// web的这个容器允许多次刷新
//		AnnotationConfigWebApplicationContext context1 = new AnnotationConfigWebApplicationContext();
		context1.register(AppConfig.class);

		context1.refresh();
		// AnnotationConfigApplicationContext在源码中是不允许refresh多次的，所以会报错
//		context1.refresh();

		System.out.println(context1.getBean("bean1"));


	}

}