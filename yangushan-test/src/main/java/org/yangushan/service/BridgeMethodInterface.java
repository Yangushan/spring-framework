package org.yangushan.service;

/**
 * 用来测试桥接方法
 * created by yangushan
 * 2024/3/18 12:27
 */
public interface BridgeMethodInterface<T> {

	void setOrderService(T t);

}
