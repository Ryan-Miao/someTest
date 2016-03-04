package com.test.java;

/**
 * final变量在初始化或使用的时候必须赋值；
 * final变量只能赋值一次
 * Created by mrf on 2016/3/4.
 */
public class TestFinal {
    private final String str = "abc";
    private final int anInt = 123;

    private final String str2;
    private final int anInt2;


    public TestFinal(String str2, int anInt2) {
        this.str2 = str2;
        this.anInt2 = anInt2;
    }

//    public void setStr(String str){
//        this.str2 = str;
//    }

    public static void main(String[] args) {
        TestFinal testFinal = new TestFinal("str2abc",456);
        System.out.println(testFinal.str);
        System.out.println(testFinal.str2);
        System.out.println(testFinal.anInt);
        System.out.println(testFinal.anInt2);
    }
}
