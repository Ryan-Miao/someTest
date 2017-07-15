package com.test.java8.defaultmethod;

/**
 * Created by Ryan on 2017/07/15/0015.
 */
public interface Inventory {
    default void print(){
        System.out.println(this.toString());
    }
}
