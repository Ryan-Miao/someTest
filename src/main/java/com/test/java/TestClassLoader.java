package com.test.java;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by rmiao on 9/8/2016.
 */
public class TestClassLoader {

    @Test
    public void testClassLoaderResource() throws Exception{
        ClassLoader classLoader = this.getClass().getClassLoader();
        String path = "a/log4j.properties";
        InputStream in = classLoader.getResourceAsStream(path);
        if (in!=null){
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bfr = new BufferedReader(reader);
            System.out.println(bfr.readLine());
        }else{
            System.out.println("123");
        }
    }
}
