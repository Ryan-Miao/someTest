package com.test.java;

import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Date;

/**
 * Created by rmiao on 8/24/2016.
 */
public class TestMessageFormat {

    @Test
    public void testDateNum() throws Exception{
        String message = "On the test run at {0,time} on {0,date}, we found {1} prime numbers";
        String format = MessageFormat.format(message, new Date(2016-1900,8-1,24,19,29,51), 12);
        Assert.assertEquals("On the test run at 19:29:51 on 2016-8-24, we found 12 prime numbers", format);
    }

    @Test
    public void testStrings() throws Exception{
        String message = "String: {0}\n Integer: {1} \n Double: {2}";
        String ryan = MessageFormat.format(message, "Ryan", new Integer(123), new Double(12.34));
        System.out.println(ryan);
        Assert.assertEquals("String: Ryan\n" +
                " Integer: 123 \n" +
                " Double: 12.34", ryan);
    }

    @Test(expected = IllegalArgumentException.class )
    public void testPatten() throws Exception{
        String message = "String key patter: {name}";
        String names = MessageFormat.format(message, "names");
        System.out.println(names);
    }

    @Test
    public void testQuote() throws  Exception{
        String message = "I'm {0}.";
        String ryan = MessageFormat.format(message, "Ryan");
        System.out.println(ryan);
        Assert.assertEquals("Im {0}.", ryan);


        message = "I''m {0}.";
        ryan = MessageFormat.format(message, "Ryan");
        System.out.println(ryan);
        Assert.assertEquals("I'm Ryan.", ryan);
    }

    @Test
    public void testReplace(){
        System.out.println("I'm Ryan.".replace("'","''"));
    }
}
