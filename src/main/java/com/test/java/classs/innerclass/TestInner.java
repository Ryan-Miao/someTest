package com.test.java.classs.innerclass;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ryan Miao on 10/19/17.
 */
public class TestInner {
    public static  volatile int b;
    private static int a;

    public static void main(String[] args) {
        System.out.println("".length());
    }

    public void t(){

    }
    public String t(int i){
        return "";
    }

    @Test
    public void test(){
        Set<A> set = new HashSet<>();
        set.add(new A());
        set.add(new A());
        System.out.println(set.size());
    }

    class A{

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
