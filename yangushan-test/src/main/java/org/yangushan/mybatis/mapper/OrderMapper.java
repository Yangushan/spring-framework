package org.yangushan.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * created by yangushan
 * 2024/3/21 16:43
 */
public interface OrderMapper {

	@Select("select 'order'")
	String test();

}
