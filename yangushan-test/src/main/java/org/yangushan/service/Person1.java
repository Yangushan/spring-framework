package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 为了测试依赖注入中的缓存准备的类
 * created by yangushan
 * 2024/3/17 21:43
 */
@Component
@Scope(value = "prototype")
public class Person1 {

	@Autowired
	private Student student;

	public void test() {
		System.out.println(student);
	}

}
