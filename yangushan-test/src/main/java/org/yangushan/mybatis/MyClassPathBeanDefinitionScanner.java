package org.yangushan.mybatis;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.yangushan.mybatis.mapper.OrderMapper;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * created by yangushan
 * 2024/3/21 17:29
 */
public class MyClassPathBeanDefinitionScanner extends ClassPathBeanDefinitionScanner {
	public MyClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
		super(registry);
	}

	/**
	 * 会通过这个方法去扫描，但是里面有一些判断，需要我们去实现
	 * @param basePackages the packages to check for annotated classes
	 * @return
	 */
	@Override
	protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
		Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
		// 这里帮我们扫描到了我们需要的类
		if (beanDefinitionHolders != null && !beanDefinitionHolders.isEmpty()) {
			beanDefinitionHolders.forEach(beanDefinitionHolder -> {
				// 在这里进行循环遍历
				GenericBeanDefinition beanDefinition = (GenericBeanDefinition) beanDefinitionHolder.getBeanDefinition();
				String clazzName = beanDefinition.getBeanClassName();
				beanDefinition.setBeanClass(MyMapperFactoryBean.class);
				beanDefinition.getConstructorArgumentValues().addGenericArgumentValue(clazzName);
			});
		}
		return beanDefinitionHolders;
	}

	@Override
	protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
		return metadataReader.getClassMetadata().isInterface();  // 返回是否是接口
	}

	@Override
	protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
		return beanDefinition.getMetadata().isInterface(); // 返回是否是接口
	}
}
