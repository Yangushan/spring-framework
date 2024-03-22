package org.yangushan.aspect.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Method;

/**
 * 同时实现多个advice，都会被调用
 * created by yangushan
 * 2024/3/22 14:37
 */
public class TwoAdvice implements MethodBeforeAdvice, MethodInterceptor {
	@Nullable
	@Override
	public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
		System.out.println("TwoAdvice invoke");
		return invocation.proceed();
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("TwoAdvice before!");
	}
}
