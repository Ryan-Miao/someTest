package com.test.java.tenum;

import java.util.Random;

/**
 * 随机抽取的工具类
 * Created by Administrator on 2016/3/30.
 */
public class Enums {
    private static Random random = new Random(47);
    public static <T extends Enum<T>> T random(Class<T> ec){
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values){
        return values[random.nextInt(values.length)];
    }
}
