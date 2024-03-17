package org.yangushan.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/17 11:40
 */
@Component
public class UserFactoryBean implements FactoryBean<User> {
	@Override
	public User getObject() throws Exception {
		return new User();
	}

	@Override
	public Class<?> getObjectType() {
		return User.class;
	}
}
