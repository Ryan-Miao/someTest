package com.test.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/2/19.
 */
public class RedisClient {

    private static String host = "127.0.0.1";
    private static int port  = 6379;

    private Jedis jedis;//非切片额客户端连接
    private JedisPool jedisPool;//非切片连接池
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池

    public RedisClient(){
        initialPool();
        initialShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();
    }

    private JedisPoolConfig getConfig(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setMaxWaitMillis(10001);
        config.setTestOnBorrow(false);
        return config;
    }

    /**
     * 初始化非切片池
     */
    private void initialPool() {
        jedisPool = new JedisPool(getConfig(),host,port);
    }

    /**
     * 初始化切片池
     */
    private void initialShardedPool(){
        List<JedisShardInfo> shards = new ArrayList<>();
        shards.add(new JedisShardInfo(host,port,"master"));
        shardedJedisPool = new ShardedJedisPool(getConfig(),shards);
    }

    public void show() {
        KeyOperate();
        StringOperate();
        ListOperate();
        SetOperate();
        SortedSetOperate();
        HashOperate();
        jedisPool.returnResource(jedis);
        shardedJedisPool.returnResource(shardedJedis);
    }

    private void HashOperate() {

    }

    private void SortedSetOperate() {

    }

    private void SetOperate() {

    }

    private void ListOperate() {

    }

    private void StringOperate() {

    }

    private void KeyOperate() {

    }
}
