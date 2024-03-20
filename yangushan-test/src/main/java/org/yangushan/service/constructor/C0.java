package org.yangushan.service.constructor;

import org.springframework.stereotype.Component;
import org.yangushan.service.OrderService;

/**
 * created by yangushan
 * 2024/3/20 12:02
 */
@Component
public class C0 {

	private OrderService orderService;

	/**
	 * 案例0，不管有参数无参数，如果只有一个构造方法都会使用这个构造方法
	 */
	public C0(OrderService orderService) {
		this.orderService = orderService;
	}


//	public C0() {
//
//	}


}
