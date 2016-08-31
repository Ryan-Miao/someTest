package com.test.cache.guava;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * learning and test guava cache
 * Created by Ryan on 2016/8/31 0031.
 */
public class Example {

    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);

    public static void main(String[] args) {
        Stopwatch stopwatch = new Stopwatch();
        //get something from remote server which may spend a long time
        try {Thread.sleep(1000);} catch (InterruptedException e) {LOGGER.error("sleep error.", e);}
        ObjFromRemote obj = new ObjFromRemote("id", "subject", "body", new Date());

        //get the infomation
        System.out.println(obj);

    }
}
