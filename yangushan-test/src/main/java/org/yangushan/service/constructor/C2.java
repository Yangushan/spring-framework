package org.yangushan.service.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yangushan.service.OrderService;

/**
 * created by yangushan
 * 2024/3/20 12:02
 */
@Component
public class C2 {

	private OrderService orderService;

	/**
	 * 案例2:如果存在1个@Autowired(required=true)的情况，但是也存在其他的@Auwotired=false也会报错
	 */
//	@Autowired(required = false)
	public C2() {

	}

	@Autowired
	public C2(OrderService orderService) {
		this.orderService = orderService;
	}


}
