package com.test.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * test org.apache.commons.lang3.StringUtils
 * Created by rmiao on 7/13/2016.
 */
public class StringUtils {

    /**
     * append a sequence to String
     * @throws Exception
     */
    @Test
    public void testJoin() throws Exception{
        String[] strArr = {"a","b","c","d"};
        String join = org.apache.commons.lang3.StringUtils.join(strArr, "--");
        assertEquals("a--b--c--d",join);
    }
}
