package com.test.java8.c2;

/**
 * Created by Ryan on 2017/07/15/0015.
 */
public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
