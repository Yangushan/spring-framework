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
public class C4 {

	private OrderService orderService;
	private Student student;

	@Autowired(required = false)
	public C4() {
		System.out.println("c4:" + 1);
	}

	/**
	 * 案例4，当存在相同参数个数一样多的时候，就会比较权重，按照权重来区分谁优先
	 * 但是因为我们案例权重计算出来是一样的，所以会判断我们的bean是否是宽松模式lenientConstructorResolution=true
	 * 默认就是true，所以根据循环，第一个设置成功的会作为默认的，所以会输出c4
	 * @param student
	 */
	@Autowired(required = false)
	public C4(Student student) {
		this.student = student;

		System.out.println("c4:" + 3);
	}

	@Autowired(required = false)
	public C4(OrderService orderService) {
		this.orderService = orderService;

		System.out.println("c4:" + 2);
	}



}
