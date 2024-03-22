package org.yangushan.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yangushan.mybatis.mapper.UserMapper;

/**
 * created by yangushan
 * 2024/3/21 16:44
 */
@Component
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public void test() {
		System.out.println(userMapper.test());
	}


}
