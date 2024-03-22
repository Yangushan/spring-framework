/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aop.framework.adapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.aop.AfterAdvice;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Interceptor to wrap an after-throwing advice.
 *
 * <p>The signatures on handler methods on the {@code ThrowsAdvice}
 * implementation method argument must be of the form:<br>
 *
 * {@code void afterThrowing([Method, args, target], ThrowableSubclass);}
 *
 * <p>Only the last argument is required.
 *
 * <p>Some examples of valid methods would be:
 *
 * <pre class="code">public void afterThrowing(Exception ex)</pre>
 * <pre class="code">public void afterThrowing(RemoteException)</pre>
 * <pre class="code">public void afterThrowing(Method method, Object[] args, Object target, Exception ex)</pre>
 * <pre class="code">public void afterThrowing(Method method, Object[] args, Object target, ServletException ex)</pre>
 *
 * <p>This is a framework class that need not be used directly by Spring users.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @see MethodBeforeAdviceInterceptor
 * @see AfterReturningAdviceInterceptor
 */
public class ThrowsAdviceInterceptor implements MethodInterceptor, AfterAdvice {

	private static final String AFTER_THROWING = "afterThrowing";

	private static final Log logger = LogFactory.getLog(ThrowsAdviceInterceptor.class);


	private final Object throwsAdvice;

	/** Methods on throws advice, keyed by exception class. */
	private final Map<Class<?>, Method> exceptionHandlerMap = new HashMap<>();


	/**
	 * Create a new ThrowsAdviceInterceptor for the given ThrowsAdvice.
	 * @param throwsAdvice the advice object that defines the exception handler methods
	 * (usually a {@link org.springframework.aop.ThrowsAdvice} implementation)
	 */
	public ThrowsAdviceInterceptor(Object throwsAdvice) {
		Assert.notNull(throwsAdvice, "Advice must not be null");
		this.throwsAdvice = throwsAdvice;

		Method[] methods = throwsAdvice.getClass().getMethods();
		// 这里可以看到，先拿到了advice的所有方法
		for (Method method : methods) {
			// 然后判断这个方法的名字是否是我们指定的AFTER_THROWING方法名字
			// 并且参数只能是1个或者4个
			if (method.getName().equals(AFTER_THROWING) &&
					(method.getParameterCount() == 1 || method.getParameterCount() == 4)) {
				// 然后拿到最后一个参数也就是异常
				Class<?> throwableParam = method.getParameterTypes()[method.getParameterCount() - 1];
				if (Throwable.class.isAssignableFrom(throwableParam)) { // 如果是异常的子类
					// An exception handler to register...
					// 那么存放在我们的map里面
					this.exceptionHandlerMap.put(throwableParam, method);
					if (logger.isDebugEnabled()) {
						logger.debug("Found exception handler method on throws advice: " + method);
					}
				}
			}
		}

		if (this.exceptionHandlerMap.isEmpty()) {
			throw new IllegalArgumentException(
					"At least one handler method must be found in class [" + throwsAdvice.getClass() + "]");
		}
	}


	/**
	 * Return the number of handler methods in this advice.
	 */
	public int getHandlerMethodCount() {
		return this.exceptionHandlerMap.size();
	}


	@Override
	@Nullable
	public Object invoke(MethodInvocation mi) throws Throwable {
		try {
			return mi.proceed();
		}
		catch (Throwable ex) {
			// 如果报错了，从错误异常map里面拿到对应符合的method，如果拿到了则调用我们的方法
			Method handlerMethod = getExceptionHandler(ex);
			if (handlerMethod != null) {
				invokeHandlerMethod(mi, ex, handlerMethod);
			}
			throw ex;
		}
	}

	/**
	 * Determine the exception handle method for the given exception.
	 * @param exception the exception thrown
	 * @return a handler for the given exception type, or {@code null} if none found
	 */
	@Nullable
	private Method getExceptionHandler(Throwable exception) {
		Class<?> exceptionClass = exception.getClass();
		if (logger.isTraceEnabled()) {
			logger.trace("Trying to find handler for exception of type [" + exceptionClass.getName() + "]");
		}
		// 先拿直接匹配的类
		Method handler = this.exceptionHandlerMap.get(exceptionClass);
		// 如果拿不到则拿父类继续匹配，直到父类如果是Throwable，则跳出循环
		while (handler == null && exceptionClass != Throwable.class) {
			exceptionClass = exceptionClass.getSuperclass();
			handler = this.exceptionHandlerMap.get(exceptionClass);
		}
		if (handler != null && logger.isTraceEnabled()) {
			logger.trace("Found handler for exception of type [" + exceptionClass.getName() + "]: " + handler);
		}
		return handler;
	}

	private void invokeHandlerMethod(MethodInvocation mi, Throwable ex, Method method) throws Throwable {
		Object[] handlerArgs;
		if (method.getParameterCount() == 1) {
			handlerArgs = new Object[] {ex};
		}
		else {
			handlerArgs = new Object[] {mi.getMethod(), mi.getArguments(), mi.getThis(), ex};
		}
		try {
			method.invoke(this.throwsAdvice, handlerArgs);
		}
		catch (InvocationTargetException targetEx) {
			throw targetEx.getTargetException();
		}
	}

}
