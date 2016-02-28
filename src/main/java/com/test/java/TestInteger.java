package com.test.java;

/**
 * Created by mrf on 2016/2/28.
 */
public class TestInteger {
    /**
     * 自动装箱和拆箱是通过valueOf来实现的，integer的valueOf源码只匹配-127到128
     * @param args
     */
    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println("=======Integer============");
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);

        System.out.println("=======Double============");
        Double d1 = 12.0;
        Double d2 = 12.0;
        Double m =123456.12;
        Double n = 123456.12;
        double d3 = 12.0;
        System.out.println(m==n);
        System.out.println(d1==d2);
        System.out.println(d1==d3);
        System.out.println("=======String============");
        String a = "123";
        String b = "123";
        System.out.println(a==b);
        System.out.println(a.equals(b));

    }
}
