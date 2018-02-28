package com.test.java.list;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.test.java.lambda.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rmiao on 12/30/2016.
 */
public class TestList {

    @Test
    public void testChange(){
        Map<String, ArrayList<User>> map = Maps.newHashMap();
        String a = "a";

        map.put(a, new ArrayList<>());
        ArrayList<User> users_a = map.get(a);

        users_a.add(new User(1));

        System.out.println(map);
    }

    @Test
    public void testList(){
        final List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("a");

        final String one = list.get(0);
        final int size = list.size();

        Assert.assertEquals("a", one);
        Assert.assertEquals(4, size);
    }

    @Test
    public void testArraysAs(){
        List<String> list = Arrays.asList("a","b");
        try {
            list.add("c");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof  UnsupportedOperationException);
            return;
        }

        Assert.fail("Should not run in here");
    }



    @Test
    public void testRemove(){
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        System.out.println(integers);
        //移除索引
        integers.remove(1);
        System.out.println(integers);
    }

    @Test
    public void testRemove2(){
        List<Integer> integers = new ArrayList<>(5);
        integers.add(1);
        integers.add(2);
        integers.add(2);
        integers.add(4);
        integers.add(5);

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i)%2==0){
                integers.remove(i);
            }
        }

        System.out.println(integers);
    }

    /**
     * 报错： java.lang.UnsupportedOperationException
     */
    @Test
    public void testRemove3(){
        List<String> list = Arrays.asList("a","b");
        list.add("c");
    }

    /**
     * 报错java.util.ConcurrentModificationException
     */
    @Test
    public void testRemove4(){
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");

        for (String string : strings) {
            strings.remove(string);
        }
    }

    /**
     * 报错： java.lang.IndexOutOfBoundsException: Index: 2, Size: 2
     */
    @Test
    public void testRemove5(){
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");

        int size = strings.size();
        for (int i = 0; i < size; i++) {
            strings.remove(i);
        }

    }

    /**
     * 报错： java.util.ConcurrentModificationException
     */
    @Test
    public void testRemove6(){
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");

        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            strings.remove(next);
        }

        System.out.println(strings);
    }

    @Test
    public void testRemove7(){
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");

        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            iterator.remove();
        }

        System.out.println(strings);
    }
}
