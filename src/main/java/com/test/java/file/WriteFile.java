package com.test.java.file;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Ryan Miao on 1/24/18.
 */
public class WriteFile {
    @Test
    public void testWriteFile() throws IOException {
        final String content = "something";

        write(content, "f1.js");
    }

    private void write(String content, String fileName) throws IOException {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        final String dir = path + "temp";

        final File file = new File(dir);
        if (!file.exists()){
            file.mkdirs();
        }

        System.out.println(dir);
        Writer writer = new FileWriter(dir + File.separator + fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        bufferedWriter.close();
        writer.close();
    }


    @Test
    public void testCleanDir() throws IOException {
        write("a", "a.txt");
        write("a", "b.txt");
        write("a", "c.txt");


    }
}
