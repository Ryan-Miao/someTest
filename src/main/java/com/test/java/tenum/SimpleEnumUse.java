package com.test.java.tenum;

/**
 * 一个简单的enum实例
 * Created by Administrator on 2016/3/30.
 */
public enum SimpleEnumUse {
    NOT,MILD,MEDIUM,HOT,FLAMING
}

class TestSE{
    public static void main(String[] args) {
        SimpleEnumUse medium = SimpleEnumUse.MEDIUM;
        System.out.println(medium);
        System.out.println(medium.ordinal());
    }
}
