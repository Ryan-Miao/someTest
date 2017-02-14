package com.test.java.list;

import com.google.common.collect.Maps;
import com.test.java.lambda.entity.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void testArraysAs(){
        List<String> list = Arrays.asList("a","b");
        try {
            list.add("c");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof  UnsupportedOperationException);
        }
    }
}
