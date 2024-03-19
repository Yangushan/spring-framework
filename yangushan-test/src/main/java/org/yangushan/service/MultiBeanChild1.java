package org.yangushan.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;

/**
 * 用来测试复杂bean注入流程
 * created by yangushan
 * 2024/3/18 15:58
 */
@Component
//@Primary
@Priority(2)
public class MultiBeanChild1 extends MultiBeanSuper {


}
