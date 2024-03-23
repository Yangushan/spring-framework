package org.yangushan.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by yangushan
 * 2024/3/22 19:40
 */
@Component
public class UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private UserService userService;

	@Transactional
	public void test() {
		jdbcTemplate.execute("insert into t1(id,a,b) values (20001,1,1)");
		/**
		 * 为什么新的事务启动没有生效，因为我们使用了@Tranactional事务，spring会给这个类创建代理对象
		 * 而真正调用Test方法的是userService本身，代理对象只是在前后增加了一些代理功能
		 * 所以这个时候调用test1的是原来的bean，而不是我们想要的代理对象，这样也就会导致我们得不到自己想要的效果
		 * 因为必须要代理对象去调用test1上面才会生效
		 * 所以这里的Test1调用必须改为代理对象调用，而不是原来的方法调用
		 * 我们可以使用AopContext的方式来完成，或者自己注入自己的方式，然后拿到代理对象的userService去调用
		 */
//		test1(); // 没有任何作用，test1不会开启新的事务，因为Test1报错了，所以会导致test也报错

		// 使用代理对象的方式去调用，才会生效
		try {
			userService.test1();
		} catch(Exception e) {
			// 千万不要以为这里进行try catch了，我们test方法的内容就会插入成功
			// 是不对的，只要在一个事务中，有一个sql被回滚了都会回滚，所以这里catch是没有意义的
			// 但是如果setGlobalRollbackOnParticipationFailure设置为了false，那么就可以成功
		}

	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void test1() { // id和test冲突错误，所以插入会报错
		jdbcTemplate.execute("insert into t1(id,a,b) values (20001,1,1)");
	}




}
