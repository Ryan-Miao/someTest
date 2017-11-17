package com.test.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Ryan Miao on 11/15/17.
 */
public class Example {
    private LoadingCache<String, Double> graphs = Caffeine.newBuilder()
            .maximumSize(10_000)
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .refreshAfterWrite(1, TimeUnit.MINUTES)
            .build(this::createExpensiveGraph);

    private Double createExpensiveGraph(String key) {
        System.out.println("Running again.");
        return Math.random();
    }

    @Test
    public void testCache(){
        Example example = new Example();
        Double aDouble1 = example.graphs.get("1");
        Double aDouble2 = example.graphs.get("1");
        Double aDouble3 = example.graphs.get("1");

        Assert.assertEquals(aDouble1, aDouble2);
        Assert.assertEquals(aDouble1, aDouble3);
    }
}
