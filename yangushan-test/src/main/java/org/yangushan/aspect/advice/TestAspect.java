package org.yangushan.aspect.advice;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/22 17:48
 */
@Aspect
@Component
public class TestAspect {

	/**
	 * 定义pointcut 拦截规则
	 */
	@Pointcut("execution(public void org.yangushan.aspect.UserService.e())")
	public void pointcut() {

	}

	@Before("pointcut()")
	public void before() {
		System.out.println("TestAspect before!");
	}

	/**
	 * 在aspectj中拿到我们调用方法的参数数据
	 * (..)里面必须是两个点
	 * @param a
	 * @param b
	 */
	@After(value = "execution(public void org.yangushan.aspect.UserService.f(..)) && args(a, b)", argNames = "a,b")
	public void after(String a, String b) {
		System.out.println("TestAspect after! a=" + a + ", b=" + b);
	}

	//@After(value = "execution(public void org.yangushan.aspect.UserService.g(..)) && args(a, b, c)", argNames = "a,b,c")
	public void afterG(String a, String b, String c) {
		System.out.println("TestAspect after!");
	}


}
