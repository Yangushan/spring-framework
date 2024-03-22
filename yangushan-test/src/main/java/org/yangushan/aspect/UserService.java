package org.yangushan.aspect;

import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxy;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/22 10:37
 */
@Component
public class UserService {

	public void test() {
		System.out.println("test");
	}

	public void a() {
		System.out.println("aaaa");
		throw new NullPointerException();
	}

	public void b() {
		System.out.println("bbbb");
	}

	public void c() {
		// 拿到被代理的对象
		Object o = AopContext.currentProxy();
		System.out.println(o);
	}

	public void d(String a) {
		System.out.println("dddddddd : "+ a);
	}

	public void e() {
		System.out.println("eeeeee");
	}

	public void f(String a, String b) {
		System.out.println("a:" + a);
		System.out.println("b:" + b);
	}

	public void g(String a, String b, String c) {
		System.out.println("a:" + a);
		System.out.println("b:" + b);
		System.out.println("c:" + c);
	}

}
