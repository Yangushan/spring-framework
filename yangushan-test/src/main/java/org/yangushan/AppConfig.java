package org.yangushan;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.yangushan.service.Person;
import org.yangushan.service.constructor.C6;

/**
 * created by yangushan
 * 2024/3/16 15:26
 */
@ComponentScan("org.yangushan.service")
@EnableAspectJAutoProxy // 开启AOP
@EnableAsync // 测试循环依赖中会导致循环依赖失败的情况
public class AppConfig {

	@Bean(autowire = Autowire.BY_TYPE)
	public Person person() {
		return new Person();
	}

}
