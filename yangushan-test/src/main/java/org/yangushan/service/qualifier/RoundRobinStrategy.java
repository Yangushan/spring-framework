package org.yangushan.service.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/19 10:50
 */
@Component
//@Qualifier("roundRobin")  // 我们在这里给bean定义名字
@RoundRobin // 等同于上面的用法
public class RoundRobinStrategy implements LoadBalance {
	@Override
	public String select() {
		return "roundRobin select";
	}
}
