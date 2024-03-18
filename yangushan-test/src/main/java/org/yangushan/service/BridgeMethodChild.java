package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * created by yangushan
 * 2024/3/18 12:27
 */
public class BridgeMethodChild implements BridgeMethodInterface<OrderService> {
	private OrderService orderService;

	/**
	 * 因为这个接口的父类是一个泛型，所以我们java在生成我们这个类的时候，还会帮下面的orderService创建一个桥接方法，可以通过show Bytecode来查看源码
	 * 这个方法继承了@Autowired，桥接方法也会继承
	 * 所以当spring在判断注入方法的时候，会忽略桥接方法
	 * @param orderService
	 */
	@Override
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	/**
	 * 可以看到在class字节码中，帮我们创建了两个方法，一个是桥接方法带有bridge，包括我们在spring源码中看到的很多synthetic的判断，也会忽略这种桥接方法
	 * 桥接方法的接受参数就是object而不是orderService了，而且桥接方法上面也有@Autowired注解
	 *
	 *
	 * // access flags 0x1
	 *   public setOrderService(Lorg/yangushan/service/OrderService;)V
	 *   @Lorg/springframework/beans/factory/annotation/Autowired;()
	 *    L0
	 *     LINENUMBER 21 L0
	 *     ALOAD 0
	 *     ALOAD 1
	 *     PUTFIELD org/yangushan/service/BridgeMethodChild.orderService : Lorg/yangushan/service/OrderService;
	 *    L1
	 *     LINENUMBER 22 L1
	 *     RETURN
	 *    L2
	 *     LOCALVARIABLE this Lorg/yangushan/service/BridgeMethodChild; L0 L2 0
	 *     LOCALVARIABLE orderService Lorg/yangushan/service/OrderService; L0 L2 1
	 *     MAXSTACK = 2
	 *     MAXLOCALS = 2
	 *
	 *   // access flags 0x1041
	 *   public synthetic bridge setOrderService(Ljava/lang/Object;)V
	 *   @Lorg/springframework/beans/factory/annotation/Autowired;()
	 *    L0
	 *     LINENUMBER 9 L0
	 *     ALOAD 0
	 *     ALOAD 1
	 *     CHECKCAST org/yangushan/service/OrderService
	 *     INVOKEVIRTUAL org/yangushan/service/BridgeMethodChild.setOrderService (Lorg/yangushan/service/OrderService;)V
	 *     RETURN
	 *    L1
	 *     LOCALVARIABLE this Lorg/yangushan/service/BridgeMethodChild; L0 L1 0
	 *     MAXSTACK = 2
	 *     MAXLOCALS = 2
	 *
	 *
	 */

}
