package com.test.java8.streams;

import com.google.common.collect.Lists;
import com.test.java8.streams.entity.Dish;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Ryan Miao on 12/11/17.
 */
public class StreamExample {

    private List<Dish> menu;

    @Before
    public void setUp(){
        menu = Lists.newArrayList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }

    @Test
    public void demo(){
        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
//                .sorted((o1,o2) -> o2.getCalories() - o1.getCalories())
//                .sorted(Comparator.comparingInt(Dish::getCalories).reversed())
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        System.out.println(threeHighCaloricDishNames);
    }

    @Test
    public void testDistinct(){
        List<Integer> list = Lists.newArrayList(1,2,3,4,1,2,3);
        List<Integer> collect = list.stream()
                .distinct()
                .collect(toList());
        System.out.println(collect);
    }
}
