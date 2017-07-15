package com.test.java8.c2;

/**
 * Created by Ryan on 2017/07/15/0015.
 */
public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "green".equals(apple.getColor());
    }
}
