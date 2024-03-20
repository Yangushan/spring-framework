package org.yangushan.service.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yangushan.service.OrderService;

/**
 * created by yangushan
 * 2024/3/20 12:02
 */
@Component
public class C1 {

	private OrderService orderService;

	@Autowired
	public C1(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * 不写required默认就是true
	 * 案例1:如果存在多个@Autowired(required=true)的情况，会报错
	 */
//	@Autowired
	public C1() {

	}


}
