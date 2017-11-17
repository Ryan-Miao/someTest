package com.test.java.basic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan Miao on 11/16/17.
 */
public class TestLong {
    @Test
    public void testLongImmutable(){
        Long a = 1L;
        Long b = change(a);
        Assert.assertNotEquals(a, b);
    }

    private Long change(Long a) {
        a = 2L;
        return a;
    }

    @Test
    public void testListLongCopy(){
        final List<Long> alist = new ArrayList<>();
        alist.add(1L);
        alist.add(2L);
        alist.add(3L);

        final List<Long> blist = new ArrayList<>();
        blist.add(4L);
        blist.add(5L);
        blist.add(6L);

        List<Long> r = new ArrayList<>(alist.size()+blist.size());
        r.addAll(alist);
        r.addAll(blist);

        System.out.println(r.size());

    }
}
