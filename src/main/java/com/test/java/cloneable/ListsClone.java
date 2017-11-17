package com.test.java.cloneable;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ryan Miao on 11/16/17.
 */
public class ListsClone {

    @Test
    public void testClone_is_浅拷贝(){
        List<Entity> list = new ArrayList<>(3);
        list.add(new Entity("1", "Ryan"));
        list.add(new Entity("1", "Miao"));
        list.add(new Entity("1", "Cloneable"));

        List<Entity> dest = new ArrayList<>(Arrays.asList(new Entity[3]));
        System.out.println(dest.size());
        Collections.copy(dest, list);

        System.out.println(list);
        System.out.println(dest);

        Assert.assertEquals(list.get(0), dest.get(0));
    }

    @Test
    public void testClone_is_deep(){
        List<Entity> list = new ArrayList<>(3);
        list.add(new Entity("1", "Ryan"));
        list.add(new Entity("1", "Miao"));
        list.add(new Entity("1", "Cloneable"));

        List<Entity> collect = list.stream()
                .map(e -> new Entity(e.getId(), e.getName()))
                .collect(Collectors.toList());

        System.out.println(list);
        System.out.println(collect);

        Assert.assertNotEquals(list.get(0), collect.get(0));
    }
}
