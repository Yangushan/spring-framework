package org.yangushan.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 定义一个动态的FactoryBean
 * created by yangushan
 * 2024/3/21 16:59
 */
public class MyMapperFactoryBean <T> implements FactoryBean<T> {

	private Class<T> clazz;

	private SqlSession sqlSession;

	// 测试打开
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		sqlSessionFactory.getConfiguration().addMapper(clazz);
		sqlSession = sqlSessionFactory.openSession();
	}

	@Override
	public T getObject() throws Exception {
//		Object o = Proxy.newProxyInstance(MyMapperFactoryBean.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
//			@Override
//			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//				return null;
//			}
//		});
//		return (T) o;

		// 使用Mybatis的代理对象
		return sqlSession.getMapper(clazz);
	}

	@Override
	public Class<T> getObjectType() {
		return clazz;
	}

	public MyMapperFactoryBean(Class<T> clazz) {
		this.clazz = clazz;
	}

}
