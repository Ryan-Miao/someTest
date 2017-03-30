package com.test.java.classs;

/**
 * 测试java类的生命周期
 * Created by Ryan on 2017/3/30/030.
 */
public class InitOrderTest {
    public static String name = "aa";//1

    static {
        System.out.println("开始static域，name="+name);
        name = "static";//2
        System.out.println("结束static域，name="+name);
    }

    public InitOrderTest(){
        System.out.println("开始构造函数， name="+name);
        name="constructor";//3
        System.out.println("结束构造函数， name="+name);
    }

    public static void main(String[] args) {
        InitOrderTest test = new InitOrderTest();
    }
}
