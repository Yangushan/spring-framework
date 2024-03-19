package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/18 19:09
 */
@Component
public class SelfBeanSuper {

	/**
	 * 自己注入自己，但是在Spring注入的流程中，自己注入自己会先判断符合条件的所有bean
	 * 所以会把子类也找出来，只有全部符合的bean都不满足的情况下才会选择使用当前的bean注入自己
	 * 因为这个类存在子类了，所以这里的selfBeanSuper会是子类，而不是当前类
	 */
	@Autowired
	private SelfBeanSuper selfBeanSuper;

	public void test() {
		System.out.println(selfBeanSuper);
	}


}
