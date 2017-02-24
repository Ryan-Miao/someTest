package com.test.java8.lambda;

import com.test.constant.Week;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

/**
 * Created by rmiao on 2/24/2017.
 */
public class FindAny {
    @Test
    public void testFindAny(){

        Week[] values = Week.values();
        Optional<Week> any = Arrays.stream(values)
                .filter(day -> day.getNum() == 3)
                .findAny();
        Assert.assertTrue(any.isPresent());
        Assert.assertEquals(Week.WEDNESDAY, any.get());
    }
}
