package com.heshan.framework.common.redis.config;

import com.heshan.framework.common.redis.exception.RedisException;
import redis.clients.jedis.JedisShardInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">frank</a>
 * @version V1.0
 * @date 2016/3/11
 */
public class RedisShardInfo {
    public RedisShardInfo(String shardInfo) {
        if (shardInfo == null || "".equals(shardInfo)) {
            throw new NullPointerException("framework-redis shardInfo");
        }
        String[] shardInfos = shardInfo.split(",");
        jedisShardInfo = new ArrayList<JedisShardInfo>();
        for (String info : shardInfos) {
            if (info == null || "".equals(info)) {
                continue;
            }
            String[] infoArray = info.split(":");
            if (infoArray.length == 3) {
                setJedisShardInfo(infoArray[0], Integer.parseInt(infoArray[1]), infoArray[2]);
            } else {
                setJedisShardInfo(infoArray[0], Integer.parseInt(infoArray[1]), null);
            }
        }
    }

    private void setJedisShardInfo(String host, int port, String auth) {
        JedisShardInfo jedisInfo = new JedisShardInfo(host, port);
        if (auth != null && !"".equals(auth))
            jedisInfo.setPassword(auth);
        if (jedisShardInfo == null) {
            jedisShardInfo = new ArrayList<JedisShardInfo>();
        }
        jedisShardInfo.add(jedisInfo);
    }

    private List<JedisShardInfo> jedisShardInfo;

    public List<JedisShardInfo> getJedisShardInfo() {
        if (jedisShardInfo == null || jedisShardInfo.size() == 0) {
            throw new RedisException("framework-redis jedisShardInfo");
        }
        return jedisShardInfo;
    }

}
