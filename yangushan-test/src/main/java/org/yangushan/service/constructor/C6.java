package org.yangushan.service.constructor;

import org.yangushan.service.OrderService;
import org.yangushan.service.Student;

/**
 * created by yangushan
 * 2024/3/20 12:02
 */
public class C6 {

	private OrderService orderService;
	private Student student;

	public C6() {
		System.out.println("c6:" + 1);
	}

	/**
	 * C6的bean在main2里面进行注入的
	 *
	 * 案例6，当我们的构造方法中，没有任何的@Autowired注解的时候，但是在注入bean的时候设置了autowired=CONSTRUCTOR，那么
	 * 这里的流程和前面一样，会拿到所有的构造方法
	 * 然后按照构造方法的参数排序，并且找到权重最小的那个构造方法进行使用，所以这里输出的是：3
	 *
	 * @param student
	 */
	public C6(Student student) {
		this.student = student;

		System.out.println("c6:" + 3);
	}

	public C6(OrderService orderService) {
		this.orderService = orderService;

		System.out.println("c6:" + 2);
	}



}
