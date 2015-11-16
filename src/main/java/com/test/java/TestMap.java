package com.test.java;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
public class TestMap {
    /**
     * map插入相同key问题，value会不会覆盖
     */

    public void testMap(){
        //HashMap中key的内容相同，则覆盖
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("张三",1);
        map1.put("张三",2);
        map1.put(new String("张三"),3);  //根据String特性，这三条内容相同，前两条地址相同
        map1.put("李四", 4);
        for (String s : map1.keySet()) {
            System.out.println(s+"======"+map1.get(s));
        }
        /** 结果
         *   张三======3
             李四======4
         */

        System.out.println("=====================================");
        //IdentityHashMap中key的内存地址必须完全相同才会覆盖
        Map<String,Object> map2 = new IdentityHashMap<String,Object>();
        map2.put("张三",1);
        map2.put("张三",2);
        map2.put(new String("张三"),3);//
        map2.put("李四", 4);
        for (String s : map2.keySet()) {
            System.out.println(s+"===="+map2.get(s));
        }
        /**
         * 李四====4
         张三====2
         张三====3
         */

        System.out.println("=====================================");

        Map<Person,Object> map3 = new IdentityHashMap<Person,Object>();
        map3.put(new Person("张三", 11), 1);
        map3.put(new Person("张三", 11), 3);
        map3.put(new Person("李四", 11), 4);
        for (Person s : map3.keySet()) {
            System.out.println(s.toString()+"===="+map3.get(s));
        }
        /**
         * po.Person@165474cf====1
         po.Person@3ff2caf4====4
         po.Person@2c0cd7d====3
         */
        System.out.println("=====================================");
        Person person = new Person("张三", 11);
        Person person2 = new Person("张三", 11);
        System.out.println(person.equals(person2));

    }

    /**
     * 测试当key不存在
     */

    public void nullKey(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a",1);
        map.put("b",1);
        map.put("c",1);
        map.put("a",2);//覆盖原来的
        for (String s : map.keySet()) {
            System.out.println(s+"===="+map.get(s));
        }
        //结果：null------返回，不会空指针异常
        System.out.println(map.get("m"));
    }
}
