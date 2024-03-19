package org.yangushan.service.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/19 10:49
 */
@Component
//@Qualifier("random") // 我们在这里给bean定义名字
@Random // 等同于上面
public class RandomStrategy implements LoadBalance {
	@Override
	public String select() {
		return "random select";
	}
}
