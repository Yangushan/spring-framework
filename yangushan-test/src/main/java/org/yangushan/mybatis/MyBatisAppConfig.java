package org.yangushan.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.io.InputStream;

/**
 * created by yangushan
 * 2024/3/21 16:44
 */
@ComponentScan("org.yangushan.mybatis")
@MapperScan("org.yangushan.mybatis.mapper")
public class MyBatisAppConfig {

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws IOException {
		InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}

}
