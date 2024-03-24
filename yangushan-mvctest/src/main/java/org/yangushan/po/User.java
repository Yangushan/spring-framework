package org.yangushan.po;

import java.util.Date;

/**
 * created by yangushan
 * 2024/3/23 18:21
 */
public class User {

	private String name;

	private Date date;

	public User() {

	}

	public User(String name) {
		this.name = name;
	}

	public User(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
