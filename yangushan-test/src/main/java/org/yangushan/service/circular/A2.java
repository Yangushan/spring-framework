package org.yangushan.service.circular;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 测试构造器循环依赖
 * created by yangushan
 * 2024/3/19 19:13
 */
@Component
public class A2 {

	private B2 b2;

	/**
	 * 我们循环依赖之所以能解决是因为我们可以创建对象，尽管它还没初始化完成，但是也可以使用
	 * 但是如果使用构造器的循环依赖就无法完成，因为我们的对象甚至都没办法创建
	 *
	 * 但是这里依然可以使用@Lazy的注解去解决这个问题，因为我们在创建A2的时候发现需要注入B2，但是因为我们使用了@lazy注解
	 * 所以spring会先给b2创建代理对象去解决就不存在循环依赖的问题了
	 *
	 * @param b2
	 */
	@Lazy
	public A2(B2 b2) {
		this.b2 = b2;
	}
}
