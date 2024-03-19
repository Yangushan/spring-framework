package org.yangushan.service;

/**
 * created by yangushan
 * 2024/3/17 21:43
 */

public class Person {

	private Student student;

	/**
	 * 我在AppConfig里面设置了person的bean
	 * 因为设置了autowired属性，所以person的student属性尽管
	 * 没有使用@Autowired等方式注入，但是依然会被设置到属性
	 * @param student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	public void test() {// 输出内容
		System.out.println(student);
	}

}
