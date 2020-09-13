package com.heshan.framework.common.redis;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="mailto:heshan664754022@gmail.com">Frank</a>
 * @version V1.0
 * @date 2016/3/14
 */
public class ShardedJedisPoolTest {

     static ShardedJedisPool pool;
    private static int index = 1;
    public static String generateKey(){
        return String.valueOf(Thread.currentThread().getId())+"_"+(index++);
    }

    static{
        JedisPoolConfig config =new JedisPoolConfig();//Jedis池配置
        config.setMaxTotal(500); //最大连接数
        config.setMaxIdle(500);// 对象最大空闲时间
        config.setMaxWaitMillis(3000L);// 获取对象时最大等待时间
        config.setTestOnBorrow(false);
        config.setTestWhileIdle(true);
        String hostA = "127.0.0.1";
        int portA = 6379;
        List<JedisShardInfo> jdsInfoList =new ArrayList<JedisShardInfo>(1);
        JedisShardInfo infoA = new JedisShardInfo(hostA, portA);
        //infoA.setPassword("test123");
        jdsInfoList.add(infoA);
        pool =new ShardedJedisPool(config, jdsInfoList);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //for(int i=0; i<10; i++){
            String key =generateKey();
            //key += "{aaa}";
            ShardedJedis jds =null;
            try {
                 jds =pool.getResource();
                //System.out.println(key+":"+jds.getShard(key).getClient().getHost());
                System.out.println(jds.set(key,"1111111111111111111111111111111"));
                System.out.println(jds.setnx("frank","11"));
                System.out.println(jds.setnx("frank","22"));
                List<String> flag=jds.blpop(2,"email1");
                System.out.println(flag);
                jds.rpush("email1","11");
                TimeUnit.SECONDS.sleep(2);
                jds.rpush("email1","22");
                List<String> flag1=jds.blpop(5,"email1");
                System.out.println(flag1);

            }catch (Exception e) {
                e.printStackTrace();
            }
            finally{
                pool.returnResource(jds);
            }
       // }
    }
     class  Task implements Runnable{
         ShardedJedis jds=null;
         public Task(ShardedJedis jds){
             this.jds=jds;
         }

         public void run() {
             List<String> flag=jds.blpop(30,"email");
             SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
             if(null!=flag&&!flag.isEmpty()){
                 System.out.println(sdf.format(new Date())+":"+flag.toArray());
             }
         }
     }
  }