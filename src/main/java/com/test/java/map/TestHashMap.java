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
    public void testHashMapShouldBeOrderedButNotAlpha(){
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

        System.out.println(unsortMap);
    }

    @Test
    public void testHashMapSortByValueDesc(){
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
        System.out.println(orderMap);
    }
}
