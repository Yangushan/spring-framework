package org.yangushan.service.constructor;

import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/20 15:44
 */
public class ReplacedTest {

	// 这里的test方法不会被执行，因为在xml中指定了replace-with
	public void test() {
		System.out.println("ReplacedTest test!");
	}

}
