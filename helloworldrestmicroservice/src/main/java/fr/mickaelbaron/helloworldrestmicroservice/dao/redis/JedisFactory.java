package fr.mickaelbaron.helloworldrestmicroservice.dao.redis;

import javax.inject.Singleton;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Mickael BARON
 */
@Singleton
public class JedisFactory {

	private static final String REDIS_HOST_ENV_KEY = "REDIS_HOST";

	private JedisPool jedisPool;
	
	public JedisFactory() {
		jedisPool = new JedisPool(new JedisPoolConfig(), getRedisHost());
	}
	
	public Jedis getJedis() {
		return jedisPool.getResource();
	}
	
	private String getRedisHost() {
		String redisHost = System.getenv(REDIS_HOST_ENV_KEY);
		
		if (redisHost == null || redisHost.isEmpty()) {
			return "localhost";
 		} else {
 			return redisHost;
 		}
	}
}
