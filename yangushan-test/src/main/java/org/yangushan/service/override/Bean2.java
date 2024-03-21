package org.yangushan.service.override;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/21 15:27
 */
@Component
public class Bean2 {

	/**
	 * 这里创建的bean1会覆盖@Component创建的哪个bean1，
	 * 因为在解析configuration的过程中，先会解析@Component的类
	 * 然后在去完成因为@Bean完成的BeanDefinition，会覆盖前面的，getBean得到的bean1是这里的
	 * @return
	 */
	@Bean
	public Bean1 bean1() {
		Bean1 bean1 = new Bean1();
		bean1.setName("bean2的bean1");
		return bean1;
	}

}
