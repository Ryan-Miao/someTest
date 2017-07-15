package com.test.java8.c2;

/**
 * Created by Ryan on 2017/07/15/0015.
 */
public class AppleRedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return "red".equals(apple.getColor()) && apple.getWeight() >150;
    }
}
