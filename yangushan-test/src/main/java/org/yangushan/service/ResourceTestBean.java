package org.yangushan.service;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 作为测试@Resource 注入流程的类
 * created by yangushan
 * 2024/3/19 14:14
 */
@Component
public class ResourceTestBean {

	// 第一种情况，我们指定了一个name，那么会先采用我们指定的name去bean里面找，找到的时候注入发现class不匹配，所以报错
//	@Resource(name = "person")
//	private Student student;

	// 第二种情况，没有指定name,会直接使用student字段的名称作为beanName去查找，发现有这个bean直接使用这个注入
	@Resource
	private Student student;

	// 第3种情况，没有指定name，会直接使用student1作为beanName去查找，发现找不到，就使用了class student去找符合的bean，所以这里会找到，并且不会报错
	@Resource
	private Student student1;

	@Resource
	private SuperBean superBean;

	@Resource
	private SuperBean superBean1;

	// 方法上使用@Resource也是一样的流程


	public void test() {
		/**
		 * 这里就体现出来了第二种和第三种的区别：
		 * 	1. 首先我们是先用字段名字去判断的，所以这里的superBean会直接找到它对应的superBean作为bean注入
		 * 	2. 但是第二个因为superBean1找不到，所以就会用SuperBean的class去查找，那么这个时候就体现出来区别了，
		 * 		会用依赖注入的流程来过滤，先找到所有符合的class所以就包含了子类，在过滤过程中，又因为子类被设置了@Primary，作为首选
		 * 		所以在superBean1中被注入的是子类child而不是父类super，这种就是不同了
		 *
		 * 	所以如果在使用@Resource的过程中，需要确定某个bean最好是设置name属性，否则就是设置好清楚字段名称，如果字段名称随便写，可能就有导致设置进去的bean并不是我们想要的那个结果
		 */
		System.out.println(superBean);
		System.out.println(superBean1);
	}

}
