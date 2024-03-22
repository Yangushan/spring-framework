package org.yangushan.mybatis;

import org.springframework.beans.factory.FactoryBean;
import org.yangushan.mybatis.mapper.UserMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 1.
 * 可以提取我们的这种思路，使用FactoryBean的方式来代理mapper
 * 但是不可能来一个mapper我们就创建一个文件
 * 所以我们可以用一个参数来接收我们具体的class类型，然后在面传入这个参数进来自己创建BeanDefinition的方式注册bean
 *
 * created by yangushan
 * 2024/3/21 16:46
 */
//@Component
public class UserMapperFactoryBean implements FactoryBean<UserMapper> {

	@Override
	public UserMapper getObject() throws Exception {
		Object o = Proxy.newProxyInstance(UserMapperFactoryBean.class.getClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
		});
		return (UserMapper) o;
	}

	@Override
	public Class<?> getObjectType() {
		return UserMapper.class;
	}
}
