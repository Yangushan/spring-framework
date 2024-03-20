package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 测试原型类型的bean循环依赖的问题
 * created by yangushan
 * 2024/3/19 19:13
 */
@Component
//@Scope(value = "prototype")
public class B3 {

	@Autowired
	private A3 a3;
}
