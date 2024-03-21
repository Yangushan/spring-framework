package org.yangushan.service.circular;

import org.springframework.stereotype.Component;

/**
 * 测试构造器循环依赖
 * created by yangushan
 * 2024/3/19 19:13
 */
@Component
public class B2 {

	private A2 a2;

	public B2(A2 a2) {
		this.a2 = a2;
	}
}
