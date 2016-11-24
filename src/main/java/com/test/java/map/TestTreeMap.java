package com.test.java.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by miaorf on 2016/11/24/024.
 */
public class TestTreeMap {
    @Test
    public void testSortAscKey(){
        TreeMap<String, Integer>  treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //升序 ascending
                return o1.compareTo(o2);
            }
        });

        treeMap.put("g",23);
        treeMap.put("b",1);
        treeMap.put("a",3);
        treeMap.put("e",13);

        Assert.assertEquals("{a=3, b=1, e=13, g=23}", treeMap.toString());
    }

    @Test
    public void testSortByValue(){
        Map<String, Integer> map = new TreeMap<>();
        map.put("v",1);
        map.put("a",4);
        map.put("d",2);
        map.put("c",3);

        List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        Assert.assertEquals("[v=1, d=2, c=3, a=4]", list.toString());
    }
}
