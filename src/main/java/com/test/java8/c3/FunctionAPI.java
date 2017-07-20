package com.test.java8.c3;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * Created by Ryan on 2017/07/19/0019.
 */
public class FunctionAPI {

    private <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    @Test
    public void testPredicate() {
        List<String> list = Arrays.asList("aa", "bbb", "ccc");
        List<String> noEmpty = filter(list, (String s) -> !s.isEmpty());
    }

    private <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    @Test
    public void testConsumer() {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        forEach(integers, System.out::println);
    }


    private <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    @Test
    public void testFunction() {
        List<String> strings = Arrays.asList("a", "bb", "ccc");
        List<Integer> lengths = map(strings, String::length);
    }

    @Test
    public void testIntPredicate() {
        //无装箱
        IntPredicate intPredicate = (int t) -> t%2 == 0;
        boolean isEven = intPredicate.test(100);
        Assert.assertTrue(isEven);
        //装箱
        Predicate<Integer> integerPredicate = (Integer i) -> i%2 == 0;
        boolean isEven2 = integerPredicate.test(100);
        Assert.assertTrue(isEven2);
    }
}
