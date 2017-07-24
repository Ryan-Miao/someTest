package com.test.java8.c3;

import com.test.java8.c2.Apple;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

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

        Predicate<Apple> red = apple -> "red".equalsIgnoreCase(apple.getColor());
        Predicate<Apple> nonRed = red.negate();
        Predicate<Apple> redAndHeavy = red.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyOrGreen = redAndHeavy.or(apple -> "green".equalsIgnoreCase(apple.getColor()));

        redAndHeavyOrGreen = ((Predicate<Apple>) apple -> "red".equalsIgnoreCase(apple.getColor()))
                .and(apple -> apple.getWeight() > 150)
                .or(apple -> "green".equalsIgnoreCase(apple.getColor()));

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
    public void testFunction2(){
        Assert.assertEquals(6, f(2));

        Function<Integer, Integer> f = x -> x +1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int r = h.apply(2);
        Assert.assertEquals(6, r);
    }

    @Test
    public void testFunction3(){
        Function<String, String> transformationPipeline =
                ((Function<String, String>)Letter::addHeader)
                        .andThen(Letter::checkSpelling)
                        .andThen(Letter::addFooter);
        String letter = transformationPipeline.apply("Hello world!");
        Assert.assertEquals("From Ryan Miao: Hello world! Kind regards", letter);
    }

    private int f(int x){
        return (x + 1) * 2;
    }

    @Test
    public void testIntPredicate() {
        //无装箱
        IntPredicate intPredicate = (int t) -> t % 2 == 0;
        boolean isEven = intPredicate.test(100);
        Assert.assertTrue(isEven);
        //装箱
        Predicate<Integer> integerPredicate = (Integer i) -> i % 2 == 0;
        boolean isEven2 = integerPredicate.test(100);
        Assert.assertTrue(isEven2);
    }
}
