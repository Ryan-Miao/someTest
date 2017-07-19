package com.test.java8.c3;

import org.junit.Test;

import java.io.*;

/**
 * Created by Ryan on 2017/07/19/0019.
 */
public class LambdaRead {

    public String read(){
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public interface BufferedReaderProcessor{
        String process(BufferedReader b) throws IOException;
    }
    public String read(BufferedReaderProcessor p) throws IOException{
        try(BufferedReader br = new BufferedReader(new FileReader("data.txt"))){
            return p.process(br);
        }
    }
    @Test
    public void readFile() throws IOException {
        String oneLine = read(BufferedReader::readLine);
        String twoLine = read((BufferedReader b) -> b.readLine() + b.readLine());
    }
}
