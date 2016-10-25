package com.test.java;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by rmiao on 10/25/2016.
 */
public class TestCompareTo {
    public static void main(String[] args) {
        Integer a = 12,b = 23;
        Assert.assertEquals(-1, a.compareTo(b));

        List<Integer> list = Arrays.asList(null,a,b,null);
        //large > small
        List<Integer> sorted = list.stream()
                .sorted((Integer m, Integer n) ->{
                    if (m==null) return 1;
                    if (n==null) return -1;
                    return n.compareTo(m);
                })
                .collect(Collectors.toList());
        Assert.assertEquals(null,sorted.get(3));
        Assert.assertEquals(null,sorted.get(2));
        Assert.assertEquals(a,sorted.get(1));
        Assert.assertEquals(b,sorted.get(0));
    }
}
