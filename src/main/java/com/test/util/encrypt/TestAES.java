package com.test.util.encrypt;


import org.junit.Test;

import java.io.*;

public class TestAES {

    private static String encoding = "utf-8";
    private static String path = System.getProperty("user.dir")+"\\src\\main\\resources\\";
    private static String origin = path+ "origin.json";
    private static String dest = path+"dest.txt";
    private static String key = "234234sdfs";

    public static void readTxt(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), encoding);
            BufferedReader bfr = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bfr.readLine()) != null) {
                System.out.println(lineTxt);
            }
            read.close();
            bfr.close();
        }
    }

    public static String readFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lineTxt = null;
        StringBuffer sb = new StringBuffer();
        while ((lineTxt = bufferedReader.readLine()) != null) {
            sb.append(lineTxt);
        }
        bufferedReader.close();
        fileReader.close();

        return sb.toString();
    }

    public static void writeTxt(String path, String data) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
        fileWriter.close();
    }

    @Test
    public void dec() throws Exception {
        System.out.println("解密：=========");
        String data = AESEncrypt.aesDecrypt(readFile(dest), key);
        writeTxt(origin,data);
        System.out.println(data);
    }

    @Test
    public void enc() throws Exception{
        System.out.println("加密");
        String data = readFile(origin);
        String result = AESEncrypt.aesEncrypt(data, key);

        writeTxt(dest,result);
        System.out.println(result);
    }

    @Test
    public void testPath(){
        String property = System.getProperty("user.dir");
        System.out.println(property);

        String file = TestAES.class.getClassLoader().getResource("").getFile();
        System.out.println(file);

        System.out.println(path);
    }

}
