package com.test.java.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by miaorf on 2016/11/24/024.
 */
public class TestHashMap {

    @Test
    public void testPut() {
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("a", 1);

        int a = 1 << 30;
        System.out.println(a);

    }

    @Test
    public void testAnd() {
       HashMap<String, Object> map = new HashMap<>(3);
       map.put("1",1);
    }

    @Test
    public void testHashMapShouldBeOrderedButNotAlpha() {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("a", 6);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("b", 5);
        unsortMap.put("f", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("c", 20);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        Assert.assertEquals("{a=6, b=5, c=20, d=1, e=7, f=9, z=10, j=50, m=2, n=99}",
                unsortMap.toString());
    }

    @Test
    public void testHashMapSortByValueDesc() {
        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("j", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        LinkedHashMap<String, Integer> orderMap = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        Assert.assertEquals("{n=99, j=50, c=20, z=10, f=9, y=8, e=7, a=6, b=5, m=2, d=1}",
                orderMap.toString());
    }

    @Test
    public void testHash() {
        Map map = new HashMap();

        int a = 1;
        int b = 0;
//        Objects.hash()
    }
}
