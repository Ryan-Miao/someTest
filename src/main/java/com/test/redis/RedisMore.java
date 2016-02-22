package com.test.redis;

import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.BitSet;

/**
 * 进一步学习redis
 * Created by Administrator on 2016/2/22.
 */
public class RedisMore {
    private static Logger log = LoggerFactory.getLogger(RedisMore.class);
    private Jedis redis;
    private RedisMore dao;
    @Before
    public void setUp(){
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        redis = new Jedis("127.0.0.1",6379);
        redis.auth("admin123");
        dao = new RedisMore();
        log.debug("连接redis:"+redis.ping());
    }

    @After
    public void afterDo(){
        redis.close();
        log.error("测试完成");
    }

    /**
     * 测试位图
     */
    @Test
    public void bitSet(){
        dao.performCount("log","2015-09-02",1L,true);
        int data = dao.uniqueCount("log", "2015-09-02");
        log.debug(""+data);
    }

    public int uniqueCount(String action,String date){
        String key = action + ":" + date;
        BitSet users = BitSet.valueOf(redis.get(key.getBytes()));
        return  users.cardinality();
    }

    public void performCount(String action,String date,long uid,boolean f){
        String key = action + ":" + date;
        redis.setbit(key,uid,f);

    }


}
