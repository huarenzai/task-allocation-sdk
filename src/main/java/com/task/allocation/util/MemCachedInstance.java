package com.task.allocation.util;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

import java.util.Date;

public class MemCachedInstance {
	
	private static MemCachedInstance memCachedInstance = null;
	MemCachedClient memcachedClient = null;

	/**
	 * 
	 * @param ip
	 * @param port
	 */
	private MemCachedInstance(String ip, int port) {
		memcachedClient = new MemCachedClient();
		// 设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器
		String[] servers = { ip + ":" + port };

		// 创建Socked连接池实例
		SockIOPool pool = SockIOPool.getInstance();
		pool.setServers(servers);// 设置连接池可用的cache服务器列表
		pool.setFailover(true);// 设置容错开关
		pool.setInitConn(10);// 设置开始时每个cache服务器的可用连接数
		pool.setMinConn(5);// 设置每个服务器最少可用连接数
		pool.setMaxConn(250);// 设置每个服务器最大可用连接数
		pool.setMaintSleep(30);// 设置连接池维护线程的睡眠时间
		pool.setNagle(false);// 设置是否使用Nagle算法，因为我们的通讯数据量通常都比较大（相对TCP控制数据）而且要求响应及时，
								// <br> 因此该值需要设置为false（默认是true） </br>
		pool.setSocketTO(3000);// 设置socket的读取等待超时值
		pool.setAliveCheck(true);// 设置连接心跳监测开关
		pool.initialize();
	}

	public static MemCachedInstance getInstance(String ip, int port) {
		if (memCachedInstance == null) {
			synchronized (MemCachedInstance.class) {
				if (memCachedInstance == null) {
					memCachedInstance = new MemCachedInstance(ip, port);
				}
			}
		}
		return memCachedInstance;
	}

	public MemCachedClient getMemCachedClient() {
		return memcachedClient;
	}
	
	public boolean setValue(String key, Object value, long expireTime) {
		// Date date=new Date(System.currentTimeMillis()+expireTime);
		boolean res = memcachedClient.set(key, value, new Date(expireTime));
		if(!res){
			res = memcachedClient.set(key, value, new Date(expireTime));
		}
		return res;
	}

	public Object getValue(String key) {	
		Object res = memcachedClient.get(key);
		if(null == res){
			res = memcachedClient.get(key);
		}
		return res;
	}
	
	public boolean delValue(String key) {
		boolean res =  memcachedClient.delete(key);
		if(!res){
			res = memcachedClient.delete(key);
		}
		return res;
	}
}
