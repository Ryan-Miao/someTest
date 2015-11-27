package com.test.java;

import com.test.reflect.ObjectCopy;

/**
 * Created by Administrator on 2015/11/26.
 * 测试对象引用
 */
public class TestRef {

    public static void main(String[] args) throws Exception {
        Country country = new Country(1,"china");
        Person person = new Person(country,1,"test");
        //引用传递
        Country country1 = country;
        Person person1 = person;
        //序列化和反序列化
        Object person2 = ObjectCopy.cloneBySer(person);
        Object country2 = ObjectCopy.cloneBySer(country);
        //反射复制
//        Object person3 = ObjectCopy.coloneByRef(person,"java.lang.Long","long");
//        Object country3 = ObjectCopy.coloneByRef(country,"java.lang.Long","long");
        
        
//        person.setAge(12);
        
        System.out.println("创建国家       ："+country);
        System.out.println("引用传递国家  ："+country1);
        System.out.println("序列化复制国家  ："+country2);
        System.out.println("创建人         ："+person);
        System.out.println("引用传递人："+person1);
        System.out.println("序列化复制人："+person2);

    }

}
