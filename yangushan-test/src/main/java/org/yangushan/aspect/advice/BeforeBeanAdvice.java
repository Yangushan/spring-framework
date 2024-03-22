package org.yangushan.aspect.advice;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * created by yangushan
 * 2024/3/22 11:30
 */
@Component
public class BeforeBeanAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("BeforeBeanAdvice before method!");
	}
}
