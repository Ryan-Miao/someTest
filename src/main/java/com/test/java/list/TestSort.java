package com.test.java.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by miaorf on 2016/11/24/024.
 */
public class TestSort {

    @Test
    public void testListSortDesc(){
        Integer a = 12,b = 23;
        Assert.assertEquals(-1, a.compareTo(b));

        List<Integer> list = Arrays.asList(null,a,b,null);
        //large > small
        List<Integer> sorted = list.stream()
                .sorted(( m, n) ->{
                    if (m==null) return 1;
                    if (n==null) return -1;
                    return n.compareTo(m);
                })
                .collect(Collectors.toList());
        Assert.assertEquals("[23, 12, null, null]", sorted.toString());
    }
}
