package org.yangushan.service;

import org.springframework.stereotype.Component;

import javax.annotation.Priority;

/**
 * 用来测试复杂bean注入流程
 * created by yangushan
 * 2024/3/18 15:58
 */
@Component
@Priority(1)
public class MultiBeanChild2 extends MultiBeanSuper {

	
}
