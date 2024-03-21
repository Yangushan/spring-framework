package org.yangushan.service.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 测试@Lazy循环依赖导致多线程场景下的空指针异常
 * created by yangushan
 * 2024/3/19 19:13
 */
@Component
@Lazy
public class B4 {

	@Autowired
	private A4 a4;
}
