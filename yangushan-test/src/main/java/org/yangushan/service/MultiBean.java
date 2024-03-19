package org.yangushan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用来测试复杂bean注入流程
 * created by yangushan
 * 2024/3/18 16:38
 */
@Component
public class MultiBean {

	/**
	 * Spring在注入@Autowired的时候，有一种情况是复杂bean的注入，也就是map（key必须是string，会注入beanName）, list等
	 * 这种情况下，spring会根据根据value进行注入，（这里不仅会找到这个类，还会找到这个类的子类）
	 * 所以我们可以通过spring的这种机制，很轻松的找到一个bean和它的所有子类
	 * 如果业务中有这种需求的话，我们就可以很快的通过spring这种强大的注入机制，找到一个类的所有子类，从而进行循环调用某个通用的方法
	 * 如果这个父类是一个接口的话，那么因为接口无法作为一个bean，所以这里就可以拿到所有的子类了
	 */
	@Autowired
	private Map<String, MultiBeanSuper> map;

	/**
	 * list和map也是一样的逻辑
	 */
	@Autowired
	private List<MultiBeanSuper> list;

	/**
	 * 这里尽管注入的是MultiBeanSuper，但是因为MultiBeanChild1使用了@Primary注入，所以下面test打印的会是child1
	 * 如果我们注释掉@Primary，那么就会使用@Priority，按照倒叙排序，所以就会打印child2
	 */
	@Autowired
	private MultiBeanSuper multiBeanSuper;

	public void test() {
		// 所以这里的map会打印出本身的super还有它所有的子类
		// {multiBeanChild1=org.yangushan.service.MultiBeanChild1@3ffcd140, multiBeanChild2=org.yangushan.service.MultiBeanChild2@23bb8443, multiBeanSuper=org.yangushan.service.MultiBeanSuper@1176dcec}
		System.out.println(map);

		System.out.println(list);

		System.out.println(multiBeanSuper);
	}


}
