package org.yangushan.service.override;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/21 15:24
 */
@Component
public class Bean1 {

	private String name;

	/**
	 * 这里如果使用@Bean注解启动会报错，@Bean不允许在一个相同名字的类里面注入
	 * @return
	 */
//	@Bean
	public Bean1 bean1() {
		System.out.println("11111");
		return new Bean1();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean1{" +
				"name='" + name + '\'' +
				'}';
	}
}
