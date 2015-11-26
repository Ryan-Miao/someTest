package com.test.java;

/**
 * Created by Administrator on 2015/11/26.
 * 测试对象引用
 */
public class TestRef {

    public static void main(String[] args) {
        Country country = new Country(1,"china");
        Person person = new Person(country,1,"test");

        Country country1 = country;
        Person person1 = person;

        System.out.println("创建国家       ："+country);
        System.out.println("引用传递国家  ："+country);
        System.out.println("创建人         ："+person);
        System.out.println("引用传递创建人："+person);



    }

}
