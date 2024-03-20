package org.yangushan.service.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yangushan.service.OrderService;
import org.yangushan.service.Student;

/**
 * created by yangushan
 * 2024/3/20 12:02
 */
@Component
public class C5 {

	private OrderService orderService;
	private Student student;

	@Autowired(required = false)
	public C5() {
		System.out.println("c5:" + 1);
	}

	/**
	 * 案例5，当存在@Autowired注解的时候，会使用@Autowired注解，并且会忽略没有写注解的构造方法，所以这个方法会被忽略
	 * 也就没有案例4的问题，所以直接根据C3一样，输出2
	 * @param student
	 */
//	@Autowired(required = false)
	public C5(Student student) {
		this.student = student;

		System.out.println("c5:" + 3);
	}

	@Autowired(required = false)
	public C5(OrderService orderService) {
		this.orderService = orderService;

		System.out.println("c5:" + 2);
	}



}
