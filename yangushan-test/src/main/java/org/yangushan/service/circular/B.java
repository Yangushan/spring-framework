package org.yangushan.service.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 测试循环依赖使用
 * created by yangushan
 * 2024/3/19 17:01
 */
@Component
public class B {

	@Autowired
	private A a;

	/**
	 * 这里的@Async不会报错，因为B后注入
	 */
	@Async
	public void async() {

	}

}
