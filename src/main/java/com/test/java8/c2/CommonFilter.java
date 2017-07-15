package com.test.java8.c2;

import com.test.java.thread.ThreadA;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Ryan on 2017/07/15/0015.
 */
public class CommonFilter {

    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        List<T> result = new ArrayList<T>();
        for (T e : list) {
            if (p.test(e)){
                result.add(e);
            }
        }

        return result;
    }

    @Test
    public void sort(){
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple("red", 100));
        appleList.add(new Apple("red", 160));
        appleList.add(new Apple("green", 60));

        appleList.sort(Comparator.comparingInt(Apple::getWeight));
    }

    @Test
    public void testRunnable(){
        Runnable runnable = () -> System.out.println("running");

        new Thread(runnable).start();
    }

    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();
        appleList.add(new Apple("red", 100));
        appleList.add(new Apple("red", 160));
        appleList.add(new Apple("green", 60));

        List<Apple> redApples = filter(appleList, (Apple apple) -> "red".equals(apple.getColor()));
        Assert.assertEquals(2, redApples.size());

        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<Integer> lessThan4Numbers = filter(numberList, (Integer num) -> num < 4);
        Assert.assertEquals(3, lessThan4Numbers.size());

    }
}
