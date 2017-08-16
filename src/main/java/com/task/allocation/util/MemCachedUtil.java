package com.task.allocation.util;

import com.task.allocation.TaskAllocationConifg;
import com.whalin.MemCached.MemCachedClient;

import java.util.Date;

public class MemCachedUtil {

	static MemCachedInstance memCachedInstance = MemCachedInstance.getInstance(
			TaskAllocationConifg.ip, TaskAllocationConifg.port);

	private static MemCachedClient memcachedClient = memCachedInstance
			.getMemCachedClient();

	/**
	 * 
	 * @param key
	 * @param obj
	 * @param expireTime
	 *            超时时间
	 */
	public static boolean setValue(String key, Object obj, long expireTime) {
		return memCachedInstance.setValue(key, obj, expireTime);
	}

	/**
	 * 获取值
	 * 
	 * @param key
	 * @return
	 */
	public static Object getValue(String key) {
		return memCachedInstance.getValue(key);
	}

	/**
	 * 添加键值对 成功返回true 重复返回false
	 * 
	 * @param key
	 * @param obj
	 * @param expiry
	 */
	public static boolean addValue(String key, Object obj, Date expiry) {
		boolean res = memcachedClient.add(key, obj, expiry);
		if(!res){
			res = memcachedClient.add(key, obj, expiry);
		}
		return res;
	}
	
	/**
	 * 删除key
	 * @param key
	 * @return
	 */
	public static boolean delete(String key) {
		boolean res = memcachedClient.delete(key);
		if(!res){
			res = memcachedClient.delete(key);
		}
		return res;
	}

	/**
	 * value做原子减法
	 * @param key
	 * @param inc
	 * @return
	 */
	public static long decr(String key, long inc) {
		long res = memcachedClient.decr(key, inc);
		if(-1 == res){
			res = memcachedClient.decr(key, inc);
		}
		return res;
	}

	/**
	 * value做原子加法
	 * @param key
	 * @param inc
	 * @return
	 */
	public static long incr(String key, long inc) {
		long res = memcachedClient.incr(key, inc);
		if(-1 == res){
			res = memcachedClient.incr(key, inc);
		}
		return res;
	}
	
	/**
	 * 判断key是否存在
	 * @param key
	 * @return
	 */
	public static boolean keyExists(String key) {
		boolean res = memcachedClient.keyExists(key);
		if(!res){
			res = memcachedClient.keyExists(key);
		}
		return res;
	}
}
