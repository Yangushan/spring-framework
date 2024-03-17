package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/16 10:05
 */
@Component
public class UserService {

	@Autowired
	private OrderService orderService;

	public void test() {
		System.out.println("test");
	}


}
