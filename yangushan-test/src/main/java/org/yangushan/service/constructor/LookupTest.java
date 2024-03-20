package org.yangushan.service.constructor;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/20 15:33
 */
@Component
public class LookupTest {

	/**
	 * 这个方法虽然什么都没有返回，但是SPring发现这个方法上有@Lookup注解
	 * 那么Spring就会给我们的LookupTest创建一个代理对象
	 *
	 * 当我们在执行这个方法的时候，就会被代理对象的LookupOverrideMethodInterceptor拦截器过滤
	 * 这个拦截器在过滤的过程中，会拿到注解上beanName去拿bean然后直接返回
	 *
	 * 所以我们虽然在Test方法中调用了这个方法，但是根本不会打印1111，而是直接打印了我们拿到的lookup对象
	 * @return
	 */
	@Lookup("lookupTest1")
	public LookupTest1 lookupTest1() {
		System.out.println("11111");
		return null;
	}

	public void test() {
		// 只会打印lookupTest1 bean，而不会打印上面的1111
		System.out.println(lookupTest1());
	}

}
