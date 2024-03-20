package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 测试循环依赖使用
 * created by yangushan
 * 2024/3/19 17:01
 */
@Component
public class A {


	/**
	 * 自己注入自己，也属于循环依赖的一种，所以循环依赖有的问题，它也会有
	 * 那么如果这里不加上lazy也会报错
	 */
	@Lazy
	@Autowired
	private A a;

	@Autowired
	/**
	 * lazy注解可以解决循环依赖过程中，产生另外代理对象导致的循环依赖报错问题
	 * 因为Lazy注解在注入的时候不会直接去getBean，而是生成代理对象直接进行返回注入
	 * 只有到真正使用到b的时候才会去真正的进行getBean注入
	 * 所以如果我们的A加载注入B，这个时候B直接返回代理对象
	 * 当我们用到b的时候A已经注册完了，所以A其实走的是一个正常的bean初始化流程，那么它被其他的BeanPostProcessor代理就不会产生任何问题
	 */
	@Lazy
	private B b;

	/**
	 * 这里涉及到依赖注入流程中到一个判断
	 *
	 * 依赖注入流程中，因为会提前开启AOP完成注入
	 * 所以在初始化完成之后，会判断bean是否有被其他的BeanPostProcessor流程来判断是否报错
	 * 因为A先加载，然后注入b，b又加载a导致A提前初始化了
	 * 然后这个@Async也是一个BeanPostProcessor流程，并且会增加代理改变bean的对象
	 * 所以如果在这里增加@Async就会导致报错，提示循环依赖
	 *
	 * 	但是在B哪里如果注入@Async就不会报错，因为B不会被提前AOP属于正常的AOP流程，所以尽管B有@Async也不影响
	 *
	 * 只有提前被AOP注入的那个bean对象才会被影响
	 *
	 */
	@Async
	public void async() {

	}

}
