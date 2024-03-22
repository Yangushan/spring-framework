package org.yangushan.mybatis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.yangushan.mybatis.*;

public class Main4 {

	public static void main(String[] args) {

		AnnotationConfigApplicationContext context1 = new AnnotationConfigApplicationContext();

		context1.register(MyBatisAppConfig.class);

		// 2. 通过构建不同的BeanDefinition进行注册来完成
		// 但是这种方式非常的不优雅，所以使用BeanDefinitionRegistryPostProcessor的方式来注册我们的beanDefinition
//		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
//		beanDefinition.setBeanClass(MyMapperFactoryBean.class);
//		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
//		context1.registerBeanDefinition("userMapper", beanDefinition);

		context1.refresh();


		UserService userService = (UserService) context1.getBean("userService");
		userService.test();

	}

}