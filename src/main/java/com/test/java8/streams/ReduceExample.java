package com.test.java8.streams;

import com.google.common.collect.Lists;
import com.test.java8.streams.entity.Dish;
import com.test.java8.streams.entity.Dish.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    public void demo() {
        OptionalInt rs = IntStream.rangeClosed(1, 100)
                .reduce((left, right) -> {
                    System.out.println(left + "\t" + right);
                    return left + right;
                });

        if (rs.isPresent()) {
            System.out.println("===========");
            System.out.println(rs.getAsInt());
        }
    }

    @Test
    public void getMax() {
        List<Integer> nums = Lists.newArrayList(3, 1, 4, 0, 8, 5);
        Optional<Integer> max = nums.stream().reduce((a, b) -> b > a ? b : a);
        max.ifPresent(System.out::println);

        nums.stream().reduce(Integer::max).ifPresent(System.out::println);
    }

    @Test
    public void mapReduce() {
        final ArrayList<Dish> dishes = Lists.newArrayList(
                new Dish("pork", false, 800, Type.MEAT),
                new Dish("beef", false, 700, Type.MEAT),
                new Dish("chicken", false, 400, Type.MEAT),
                new Dish("french fries", true, 530, Type.OTHER),
                new Dish("rice", true, 350, Type.OTHER),
                new Dish("season fruit", true, 120, Type.OTHER),
                new Dish("pizza", true, 550, Type.OTHER),
                new Dish("prawns", false, 300, Type.FISH),
                new Dish("salmon", false, 450, Type.FISH)
        );

        Integer sum = dishes.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b);

    }

    /**
     * 给一个初始值
     */
    @Test
    public void demoWithInit() {
        int rs = IntStream.rangeClosed(1, 100)
                .reduce(10, (a, b) -> a + b);

        System.out.println("===========");
        System.out.println(rs);
    }
}
