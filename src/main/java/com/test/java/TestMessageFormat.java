package com.test.java;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by rmiao on 8/24/2016.
 */
public class TestMessageFormat {

    @Test
    public void testMessage() throws Exception{
        String message = "On the test run at {0,time} on {0,date}, we found {1} prime numbers";
        String format = MessageFormat.format(message, new Date(), 12);
        System.out.println(format);
    }
}
