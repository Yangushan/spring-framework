package org.yangushan.service.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/19 10:51
 */
@Component
public class LoadBalanceTestBean {

	@Autowired
//	@Qualifier("roundRobin")  // 指定选择的bean，但是这个情况就比较麻烦，因为我们每次注入loadBalance的时候都需要去指定这个名字
								// 所以@Qualifier还支持我们自己自定义的注解的方式去使用
	@RoundRobin // 这里就等同于上面
	private LoadBalance loadBalance;

	public void test() {
		System.out.println(loadBalance.select());
	}

}
