package com.frank.utils.redis;

import com.frank.utils.redis.config.RedisShardInfo;
import com.frank.utils.redis.config.ShardedRedisPool;
import org.apache.log4j.Logger;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 私有云专有.
 * 
 * @version : Ver 1.0
 * @author : <a href="mailto:wangtao@ebnew.com">wangtao</a>
 * @date : 2015-7-29 下午6:14:53
 */
public class BidRedisFactory {
	private static BidRedis bidRedis = null;

	private BidRedisFactory() {

	}

	/**
	 * 初始化.
	 * 
	 * @author : <a href="mailto:wangtao@ebnew.com">wangtao</a> 2015-8-21
	 *         上午9:18:49
	 * @return
	 */
	private static BidRedis initBidRedis() {
		//logger.info("初始化Redis连接:" + BidRedisConstant.getRedisConf());
		RedisShardInfo redisShardInfo = new RedisShardInfo(
				BidRedisConstant.getRedisConf());
		// 配置连接池
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(500);
		config.setMaxIdle(50);
		config.setMaxWaitMillis(3000L);
		config.setTestOnBorrow(false);
		config.setTestWhileIdle(true);
		ShardedRedisPool shardedJedisPool = new ShardedRedisPool(config,
				redisShardInfo);
		BidRedisImpl bidRedisImpl = new BidRedisImpl();
		bidRedisImpl.setShardedJedisPool(shardedJedisPool);
		return bidRedisImpl;
	}

	public static BidRedis getBidRedis() {
		if (bidRedis == null) {
			synchronized (BidRedisFactory.class) {
				if (bidRedis == null) {
					bidRedis = initBidRedis();
				}
			}
		}
		return bidRedis;
	}

	public static void main(String[] args) {

		BidRedis bidRedis=BidRedisFactory.getBidRedis();
		System.out.println(bidRedis.getString("c3af9374694b6a15f7766234e6d3f126_JZ_REG_USER_cgs1021llt"));

	}


}
