package org.yangushan.mybatis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.yangushan.mybatis.mapper.OrderMapper;
import org.yangushan.mybatis.mapper.UserMapper;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * 4. 由于ImportBeanDefinitionRegistrar可以很好的拿到一些枚举信息，更适合我们
 * created by yangushan
 * 2024/3/21 17:21
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
		// 可以拿到注解信息
		Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MapperScan.class.getName());
		String path = null;
		if (annotationAttributes != null && annotationAttributes.containsKey("value")) {
			path = (String) annotationAttributes.get("value");
		}
		System.out.println(path);

		// 拿到路径就想着去解析，spring中有解析的类，我们自己去继承就好了
		MyClassPathBeanDefinitionScanner scanner = new MyClassPathBeanDefinitionScanner(registry);
		scanner.scan(path);
	}
}
