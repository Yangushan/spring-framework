package org.yangushan.service;

import org.springframework.stereotype.Component;

/**
 * created by yangushan
 * 2024/3/17 10:05
 */
@Component
public class User {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
