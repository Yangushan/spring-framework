package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 泛型bean测试父类
 * created by yangushan
 * 2024/3/19 10:14
 */
public class GenericSuper<O, S> {

	@Autowired
	protected O o ;
	@Autowired
	protected S s;

	public void test() {
		System.out.println("o：" + o);
		System.out.println("s：" + s);
	}

}
