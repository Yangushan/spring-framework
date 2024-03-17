package org.yangushan.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 用来测试spring创建bean对象的时候，使用我们自定义的这个beanPostProcessor来创建bean
 * 如果这个里面的postProcessBeforeInstantiation方法不返回null，spring就会用这个方法来new bean
 * created by yangushan
 * 2024/3/17 19:34
 */
@Component
public class TestInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

	/**
	 * 这个方法就是spring在new bean之前会调用的，如果不返回空，spring会用我们new的这个对象来当作bean对象使用
	 * 所以这也是为什么这个方法的参数是beanClass而不是object,因为这个时候对象还没创年呢
	 * @param beanClass the class of the bean to be instantiated
	 * @param beanName the name of the bean
	 * @return
	 * @throws BeansException
	 */
	@Override
	public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
		if (beanName.equals("user")) {
			User user = new User();
			user.setName("TestInstantiationAwareBeanPostProcessor");
			return user;
		}
		// 返回null,表示spring不会使用这个方法来new对象
		return null;
	}
}
