package org.yangushan;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.yangushan.service.Person;

/**
 * created by yangushan
 * 2024/3/16 15:26
 */
@ComponentScan("org.yangushan.service")
public class AppConfig {

	@Bean(autowire = Autowire.BY_TYPE)
	public Person person() {
		return new Person();
	}

}
