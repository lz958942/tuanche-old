package com.tuanche.baseapi.web.interceptor.collector;

/**
 * 收集器.
 * @author YRJ
 *
 */
public interface Collector<T, V, R> {

	/**
	 * 收集信息.
	 * @param t
	 * @param v
	 * @return
	 */
	R collector(T t, V v);
}
