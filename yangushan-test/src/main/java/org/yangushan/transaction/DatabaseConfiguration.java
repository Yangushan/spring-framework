package org.yangushan.transaction;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * created by yangushan
 * 2024/3/22 19:41
 */
@Configuration // 如果不配置这个，则下面的数据源每次都是拿到不一样的，事务会失效，因为不会生成代理类，所以每次调用dataSource方法就是调用一次产生新的对象
public class DatabaseConfiguration {


	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
//
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(dataSource());
		// 默认设置失败回滚策略，如果设置为false，则不影响也就是同一个事务，一个sql失败了不影响成功的sql
		// 如果设置为true，则只要有失败就都失败，只要在一个事务当中，不管这个方法有没有补货异常，只要是一个Sql回滚了就一起回滚
		transactionManager.setGlobalRollbackOnParticipationFailure(false);
		return transactionManager;
	}
//
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

}
