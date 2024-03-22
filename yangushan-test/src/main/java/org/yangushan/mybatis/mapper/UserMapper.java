package org.yangushan.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * created by yangushan
 * 2024/3/21 16:43
 */
public interface UserMapper {

	@Select("select 'user'")
	String test();

}
