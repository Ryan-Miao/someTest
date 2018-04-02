package com.test.java8.streams;

import java.util.OptionalInt;
import java.util.function.IntBinaryOperator;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * Created by Ryan Miao on 4/2/18.
 */
public class ReduceExample {

    /**
     * 没有初始值，返回Optional
     */
    @Test
    public void demo(){
        OptionalInt rs = IntStream.rangeClosed(1, 100)
                .reduce((left, right) -> {
                    System.out.println(left + "\t" + right);
                    return left + right;
                });

        if (rs.isPresent()){
            System.out.println("===========");
            System.out.println(rs.getAsInt());
        }
    }

    /**
     * 给一个初始值
     */
    @Test
    public void demoWithInit(){
        int rs = IntStream.rangeClosed(1, 100)
                .reduce(10, (a, b) -> a + b);

        System.out.println("===========");
        System.out.println(rs);
    }
}
