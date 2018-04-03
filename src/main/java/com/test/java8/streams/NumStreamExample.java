package com.test.java8.streams;

import com.google.common.collect.Lists;
import com.test.java8.streams.entity.Dish;
import com.test.java8.streams.entity.Dish.Type;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

/**
 * Created by Ryan Miao on 4/3/18.
 */
public class NumStreamExample {

    @Test
    public void testToInt() {
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

        IntStream intStream = dishes.stream()
                .mapToInt(Dish::getCalories);
    }


    @Test
    public void testToObj() {
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

        IntStream intStream = dishes.stream()
                .mapToInt(Dish::getCalories);

        Stream<Integer> boxed = intStream.boxed();
    }

    @Test
    public void testOptionalInt() {
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

        OptionalInt optionalInt = dishes.stream()
                .mapToInt(Dish::getCalories)
                .max();

        int max = optionalInt.orElse(1);
    }

    @Test
    public void testNumRange(){
        IntStream intStream = IntStream.rangeClosed(1, 2);
        IntStream range = IntStream.range(1, 2);
    }



}
