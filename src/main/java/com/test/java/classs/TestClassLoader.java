package com.test.java.classs;

/**
 * 测试类的加载顺序
 * Created by Administrator on 2016/3/22.
 */
public class TestClassLoader {
    public static void main(String[] args) {
        System.out.println(TestStr.a);
    }
}

class TestStr{
    //为啥结果不一样
//    static final int a = 1;
    static final Integer a = 1;
    static {
        System.out.println("2");
    }
}
