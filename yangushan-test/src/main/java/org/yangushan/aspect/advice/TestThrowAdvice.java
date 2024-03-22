package org.yangushan.aspect.advice;

import org.springframework.aop.ThrowsAdvice;

import javax.servlet.ServletException;
import java.lang.reflect.Method;

/**
 * created by yangushan
 * 2024/3/22 11:40
 */
public class TestThrowAdvice implements ThrowsAdvice {


	/**
	 * 同时定义多个错误的情况下，会先找更加精准的错误
	 * @param method
	 * @param args
	 * @param target
	 * @param ex
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		System.out.println("Exception!");
	}

	/**
	 * 需要注意的是，这个方法父类并没有定义，但是只要我们在方法中根据父类的描述，写上这个名字就可以了
	 * @param method
	 * @param args
	 * @param target
	 * @param ex 如果这个参数和我们抛出异常的参数不一致，那么就不会调用，如果我们用exception接受，那么都会进入
	 */
	public void afterThrowing(Method method, Object[] args, Object target, NullPointerException ex) {
		System.out.println("NullPointerException!");
	}


}
