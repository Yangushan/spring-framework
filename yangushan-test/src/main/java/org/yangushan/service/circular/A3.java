package org.yangushan.service.circular;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 测试原型类型的bean循环依赖的问题
 * created by yangushan
 * 2024/3/19 19:13
 */
@Component
@Scope(value = "prototype")
public class A3 {

	/**
	 * 两个原型bean之间的依赖注入会报错
	 * 	因为在判断依赖注入的过程中，会判断是否是单例bean才有缓存，原型bean是没有缓存的
	 * 所以每次就是a去拿b，b又去拿a，造成死循环递归，会直接报错
	 *
	 * 但是只要其中一个不是原型bean而是单例bean就不会报错
	 * 假如我们的A是原型，B单例
	 * 因为A是原型，所以A并不会在spring容器启动的时候就初始化，会先初始化B
	 * 这个时候B发现要创建初始化A，然后去拿A的bean，然后A发现又要注入B
	 * 	这个时候就是按照我们正常的依赖注入流程走了，B会加入三级缓存一开始，然后发现B循环依赖的了放入二级缓存，提前返回给A
	 * 	A完成了注入，然后B也完成了注入放入单例池
	 *
	 * 	之后如果每次去拿A，因为B已经构建完了，所以没有任何问题
	 */
	@Autowired
	private B3 b3;
}
