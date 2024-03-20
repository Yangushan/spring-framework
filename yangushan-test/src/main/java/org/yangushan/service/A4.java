package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 测试@Lazy循环依赖导致多线程场景下的空指针异常
 * created by yangushan
 * 2024/3/19 20:06
 */
@Component
@Lazy
public class A4 {

	@Autowired
	private B4 b4;

	public void test() {
		// 这里可能会导致提前拿到一个不完成品的数据造成b4还没完成属性赋值，而导致对b4的操作很可能空指针
		System.out.println(b4.toString());
	}

}
