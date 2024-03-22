package org.yangushan.aspect.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * created by yangushan
 * 2024/3/22 11:30
 */
public class TestBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("before method!");
	}
}
