package com.test.java.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ryan Miao on 1/25/18.
 */
public class StringAPI {
    @Test
    public void testDotReplace(){
        String str = "a/b/../c";
        Assert.assertTrue(str.contains(".."));
        str = str.replace("..", "");
        Assert.assertEquals("a/b//c", str);
    }
}
