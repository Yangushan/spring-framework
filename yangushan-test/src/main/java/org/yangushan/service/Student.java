package org.yangushan.service;

import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/17 21:08
 */
@Component
public class Student {

	/**
	 * 没有任何地方主动testMergedBeanDefinitionPostProcessor方法
	 * 但是我们在TestMergedBeanDefinitionPostProcessor里面设置了这个student bean的initMethod就是这个方法
	 * 所以当我们student bean初始化的过程之中，会主动调用这个方法
	 */
	public void testMergedBeanDefinitionPostProcessor() {
		System.out.println("initMethodName test!");
	}

	private String name;

	/**
	 * 这个方法我们在MergedBeanDefinitionPostProcessor里面，设置了propertyValue
	 * 所以会自动帮我们设置这个属性的值为abc，打印就有数据了
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public void test() {
		System.out.println(name);
	}

}
