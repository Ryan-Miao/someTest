package com.test.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 学习使用redis
 * Created by Administrator on 2016/2/19.
 */
public class HelloRedis {
    private static final Jedis jedis = new Jedis("127.0.0.1",6379);

    public static void main(String[] args) {
        jedis.set("test","Hello Redis");
        String test = jedis.get("test");
        System.out.println(test);
        jedis.del("test");
    }

    /**
     * jedis存储字符串
     */
    @Test
    public void testString(){
        //--添加数据--
        jedis.set("name","miao");
        System.out.println(jedis.get("name"));
        //拼接
        jedis.append("name"," be stronger!");
        System.out.println(jedis.get("name"));

        jedis.del("name");
        System.out.println(jedis.get("name"));
        //设置多个
        jedis.mset("name","miao","age","25","birth","1991-02-22");
        jedis.incr("age");//+1
        System.out.println(jedis.get("name")+"-"+jedis.get("age")+"-"+jedis.get("birth"));
        jedis.del("name","age","birht");
    }

    /**
     * redis操作map
     */
    @Test
    public void testMap(){
        //--添加数据--
        Map<String,String> map = new HashMap<>();
        map.put("name","miao");
        map.put("age","35");
        map.put("birth","1991-02-33");
        jedis.hmset("user",map);
        //取出
        List<String> user = jedis.hmget("user","name","age","birth");
        System.out.println(user);
        Map<String, String> user1 = jedis.hgetAll("user");//取出user
        System.out.println("user1="+user1);
//        jedis.del("user");

        //删除某个属性
        jedis.hdel("user","birth");
        System.out.println(jedis.hmget("user","name","age","birth"));
        Long count = jedis.hlen("user");//user的属性的个数
        System.out.println("属性个数："+count);
        Boolean is = jedis.exists("user");//是否存在
        System.out.println("是否存在："+is);
        Set<String> ukeys = jedis.hkeys("user");//user的所有属性
        System.out.println(ukeys);
        List<String> uvals = jedis.hvals("user");//user的所有属性值
        System.out.println(uvals);

        jedis.del("user");
    }

    /**
     * redis操作list
     */
    @Test
    public void testList(){
        //查看
        List<String> users = jedis.lrange("users", 0, -1);
        System.out.println("list:"+users);
        //向左插入
        jedis.lpush("users","miao");
        jedis.lpush("users","rui");
        jedis.lpush("users","feng");
        //向右插入
        jedis.rpush("users","ming");
        jedis.rpush("users","hong");
        jedis.rpush("users","xing");
        System.out.println("（注意顺序）插入的结果："+jedis.lrange("users",0,-1));

        jedis.del("users");
        System.out.println(jedis.lrange("users",0,-1));
    }

    /**
     * jedis set
     */
    @Test
    public void testSet(){
        //添加
        jedis.sadd("user","miao");
        jedis.sadd("user","rui");
        jedis.sadd("user","feng");
        jedis.sadd("user","ming");
        jedis.sadd("user","hong");
        jedis.sadd("user","xing");
        //查看
        Set<String> user = jedis.smembers("user");
        System.out.println("set:   "+user);
        //移除
        jedis.srem("user","miao");
        System.out.println("移除后： "+jedis.smembers("user"));
        //判断是否存在
        Boolean is = jedis.sismember("user", "rui");
        System.out.println("rui是否存在： "+is);
        //随机取出一个
        String one = jedis.srandmember("user");
        System.out.println(one);
        System.out.println(jedis.srandmember("user",2));
        //返回个数
        Long count = jedis.scard("user");
        System.out.println("user个数："+count);

        jedis.del("user");

    }

    /**
     * 排序
     */
    @Test
    public void testSort(){
        jedis.del("list");
        jedis.rpush("list","1");
        jedis.rpush("list","4");
        jedis.rpush("list","2");
        jedis.rpush("list","20");
        jedis.rpush("list","5");
        jedis.rpush("list","6");
        jedis.rpush("list","67");
        System.out.println(jedis.lrange("list",0,-1));
        System.out.println("排序："+jedis.sort("list"));
        System.out.println("排序不影响原存储："+jedis.lrange("list",0,-1));
    }



}
