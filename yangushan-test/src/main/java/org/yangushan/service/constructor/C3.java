package org.yangushan.service.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yangushan.service.OrderService;

/**
 * created by yangushan
 * 2024/3/20 12:02
 */
@Component
public class C3 {

	private OrderService orderService;

	/**
	 * 案例3:如果存在多个个@Autowired(required=false)的没有关系
	 */
	@Autowired(required = false)
	public C3() {
		System.out.println("c3:" + 1);
	}

	/**
	 * 按照排序方式，会优先排序参数最多的，也就是c3，之后发现无参的参数比这个少，所以这个当选
	 * @param orderService
	 */
	@Autowired(required = false)
	public C3(OrderService orderService) {
		this.orderService = orderService;

		System.out.println("c3:" + 2);
	}


}
