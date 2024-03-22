package org.yangushan.mybatis;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.yangushan.mybatis.mapper.UserMapper;

/**
 * 3. 用注册器来进行我们mapper的BeanDefinition注册
 * created by yangushan
 * 2024/3/21 17:09
 */
//@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// 这里暂时没有什么需要
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
		beanDefinition.setBeanClass(MyMapperFactoryBean.class);
		beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
		registry.registerBeanDefinition("userMapper", beanDefinition);

		// 但是上面的这个方法总不能来一个写一个，所以我们需要用一个东西来可以拿到我们所有定义的mapper
		// 那么就实现一个注解，通过Spring拿到这个注解下的所有类，然后循环的方式来创建
		// 如果我们在所有的mapper上增加注解，这也是一种方法，但是太过于麻烦
		// 像我们的spring一样，只要某个包下面有，我们就认为这个类是一个mapper，我们用这种方式来实现
		// 但是如果使用这个方法来实现的话，在@import注解中，另外一个类更加方便，所以我们放弃这个实现方法


	}
}
