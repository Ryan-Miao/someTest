package com.test.java;

import com.test.util.encrypt.AESEncrypt;

import java.io.*;

public class TestRead {

    private static String encoding = "utf-8";

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

    public static void main(String[] args) throws Exception {
        String origin = "C:\\Users\\miaorf\\Desktop\\test.json";
        String dest = "C:\\Users\\miaorf\\Desktop\\test2.json";
        String key = "1234";
        String data = readFile(origin);
        System.out.println("读取：=========");
        System.out.println(data);
        String encrypt = AESEncrypt.aesEncrypt(data, key);
        System.out.println("加密：=========");
        System.out.println(encrypt);
        writeTxt(dest,encrypt);
        System.out.println("解密：=========");
        String result = AESEncrypt.aesDecrypt(readFile(dest), key);
        System.out.println(result);
//        writeTxt(origin,result);

    }


}
