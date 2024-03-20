package org.yangushan.service.constructor;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

/**
 * created by yangushan
 * 2024/3/20 15:47
 */
public class MyReplacer implements MethodReplacer {
	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("MyReplacer test!");
		return null;
	}
}
