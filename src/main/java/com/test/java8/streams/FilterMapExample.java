package com.test.java8.streams;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ryan Miao on 12/6/17.
 */
public class FilterMapExample {

    @Test
    public void testFilterMapLimit(){
        List<Entity> entities = Lists.newArrayList(new Entity(100), new Entity(12), new Entity(33), new Entity(41));
        List<Integer> collect = entities.stream()
                .filter(entity -> entity.getId() < 100)
                .map(Entity::getId)
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}
