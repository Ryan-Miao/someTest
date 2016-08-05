package com.test.java;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by rmiao on 8/4/2016.
 */
public class TestFile {

    @Test
    public void testCreateDir() throws Exception{
        String name = "D:\\var\\a\\b\\c";
        File file = new File(name);
        boolean mkdir = file.mkdir();
        Assert.assertFalse(mkdir);
        boolean mkdirs = file.mkdirs();
        Assert.assertTrue(mkdirs);
    }
}
