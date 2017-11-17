package com.test.java8.streams;

import com.google.common.collect.Lists;
import org.jsoup.select.Collector;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ryan Miao on 11/17/17.
 */
public class PeekExample {


    @Test
    public void testPeek(){
        final List<Integer> list = Lists.newArrayList(1,2,3,4);

        List<Entity> collect = list.stream()
                .map(Entity::new)
                .peek(e -> e.setUpdateTime(new Date()))
                .collect(Collectors.toList());

        System.out.println(collect);
    }
}
