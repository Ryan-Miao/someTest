package com.test.java;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * long的==
 * 今天遇到一个问题，List<Long>,我想判断list中contain
 *
 * Created by Ryan on 2016/5/26 0026.
 */
public class TestLong {
    private static List<Long> list = new ArrayList<>();
    static {
        for (int i = 1; i < 3; i++) {
            list.add(new Long(i));
        }
    }

    @Before
    public void setup(){
        System.out.println(list);
    }

    @Test
    public void testEqual(){
        Integer a = 12;
        Integer b = 12;
        assertEquals(a,b);
        assertTrue(a==b);

        Integer aa = 1234;
        Integer bb = 1234;
        assertEquals(aa,bb);
        assertFalse(aa==bb);

        Long m=123L;
        Long n=123L;
        assertEquals(m,n);
        assertTrue(m==n);

        Long mm = 12345L;
        Long nn = 12345L;
        assertEquals(m,n);
        assertTrue(m==n);

        assertTrue(list.contains(1L));
        assertTrue(list.contains(2L));
    }

    @Test
    public void test2(){
        Long a = new Long("123");
        Long b = 123L;
        assertEquals(a,b);
    }
}
