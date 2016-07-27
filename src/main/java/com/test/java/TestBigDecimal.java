package com.test.java;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Created by rmiao on 7/26/2016.
 */
public class TestBigDecimal {

    @Test
    public void test1() throws Exception{
        Long a = 40748366L;
        BigDecimal bigDecimal = new BigDecimal(a);
        BigDecimal divide = bigDecimal.divide(new BigDecimal(Math.pow(10, 6)));
        System.out.println(divide.toString());
        Assert.assertEquals("40.748366",divide.toString());
        BigDecimal latitude = bigDecimal.movePointLeft(6);
        System.out.println(latitude);
        Assert.assertEquals("40.748366",latitude.toString());

        BigDecimal longitude = new BigDecimal(-73974664L).movePointLeft(6);
        System.out.println(longitude.toString());


    }
}
