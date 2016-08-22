package com.test.java;

import com.test.util.encrypt.AESEncrypt;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public static Map readFile(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lineTxt = null;
        StringBuffer sb = new StringBuffer();
        List<Integer> list = new ArrayList<>(100);
        int sum = 0;
        while ((lineTxt = bufferedReader.readLine()) != null) {
            sb.append(lineTxt);
            int i = Integer.parseInt(lineTxt);
            list.add(i);
            sum++;
            System.out.println(lineTxt);
        }
        bufferedReader.close();
        fileReader.close();

        Map map = new HashMap();
        map.put("lineSize", sum);
        map.put("value", sb.toString());
        map.put("line",list);
        return map;
    }
    @Test
    public void testReadFile() throws IOException {
        String path = "C:\\Users\\rmiao\\Desktop\\taxv2-expr.txt";
        Map s = readFile(path);

        Integer[] ins = {889881};
        List<Integer> keys = Arrays.asList(ins);

        List<Integer> lines = (List<Integer>)s.get("line");
        System.out.println();
        lines.stream()
                .filter(line ->  keys.contains(line))
                .forEach(  System.out::print  );
        System.out.println(s.get("lineSize"));
    }

    public static void writeTxt(String path, String data) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(data);
        bufferedWriter.close();
        fileWriter.close();
    }

    public static void main(String[] args) throws Exception {
        List<Integer> collect = Arrays.stream(args).map(Integer::parseInt)
                .collect(Collectors.toList());
        System.out.println(collect.size());


        String origin = "C:\\Users\\miaorf\\Desktop\\test.json";
        String dest = "C:\\Users\\miaorf\\Desktop\\test2.json";
        String key = "1234";
        String data = readFile(origin).get("value").toString();
        System.out.println("读取：=========");
        System.out.println(data);
        String encrypt = AESEncrypt.aesEncrypt(data, key);
        System.out.println("加密：=========");
        System.out.println(encrypt);
        writeTxt(dest,encrypt);
        System.out.println("解密：=========");
        String result = AESEncrypt.aesDecrypt(readFile(dest).get("value").toString(), key);
        System.out.println(result);
//        writeTxt(origin,result);

    }


}
