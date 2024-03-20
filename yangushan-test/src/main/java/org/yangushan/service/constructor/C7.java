package org.yangushan.service.constructor;

import org.yangushan.service.OrderService;
import org.yangushan.service.Student;

/**
 * created by yangushan
 * 2024/3/20 12:02
 */
public class C7 {

	private OrderService orderService;
	private Student student;

	public C7() {
		System.out.println("c7:" + 1);
	}

	/**
	 * C7的bean在main2里面进行注入的,
	 * 案例7因为我们在beanDefinition中指定了我们的参数为orderService
	 * 	所以这里会输出2
	 *
	 * @param student
	 */
	public C7(Student student) {
		this.student = student;

		System.out.println("c7:" + 3);
	}

	public C7(OrderService orderService) {
		this.orderService = orderService;

		System.out.println("c7:" + 2);
	}



}
