package org.yangushan.service;

import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 用来测试MergedBeanDefinitionPostProcessor的类
 * MergedBeanDefinitionPostProcessor是当我们使用spring的new bean的流程中，在bean实例化之后就会立马调用的一个BeanPostProcessor
 * 这个流程会走在bean属性填充和初始化之前
 * 从下面的方法可以看到我们可以对BeanDefinition进行一些属性的设置，让他在初始化和属性填充之前生效
 * 由于这个时候bean已经new了，所以我们只有对一些少量的属性设置才能生效，一些已经在bean创建之前需要的属性这个时候设置已经没有任何用处了
 *
 * created by yangushan
 * 2024/3/17 20:54
 */
@Component
public class TestMergedBeanDefinitionPostProcessor implements MergedBeanDefinitionPostProcessor {
	@Override
	public void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName) {
		if (beanName.equals("student")) {
			// 设置initMethodName
			beanDefinition.setInitMethodName("testMergedBeanDefinitionPostProcessor");
			// 设置propertyValue提前给我们的bean的一些属性赋值
			beanDefinition.getPropertyValues().addPropertyValue("name", "abc ");
		}
	}
}
