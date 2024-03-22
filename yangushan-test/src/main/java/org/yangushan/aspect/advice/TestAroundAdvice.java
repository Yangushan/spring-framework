package org.yangushan.aspect.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * 使用Spring提供的MethodInterceptor
 * created by yangushan
 * 2024/3/22 11:44
 */
public class TestAroundAdvice implements MethodInterceptor {
	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		System.out.println("TestAroundAdvice before");
		Object proceed = invocation.proceed();
		System.out.println("TestAroundAdvice after");
		return proceed;
	}
}
