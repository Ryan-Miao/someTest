package com.test.arithmetic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/11/23.
 * 字符串编辑距离的实现
 */
public class DistanceOfEdit {
    public static void main(String[] args) {
        System.out.print("please input src String: ");
        InputStream inputStream = System.in;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferReader = new BufferedReader(inputStreamReader);

        //Get source string
        String srcStr = null;
        try{
            srcStr = bufferReader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        //Get destination string
        String dstStr = null;
        System.out.print("please input dst String: ");
        try{
            dstStr = bufferReader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("源字符串  ："+srcStr);
        System.out.println("目标字符串："+dstStr);

        //Display the result
        System.out.println("Edit Distance is: "
                + getEditDistance(srcStr, dstStr));

        try{
            bufferReader.close();
            inputStreamReader.close();
            inputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static int getEditDistance(String srcStr, String dstStr){
        int m = srcStr.length();
        int n = dstStr.length();
        //use one dimension array to represent two dimension
        //Initializing...
        int d[] = new int[(m + 1) * (n + 1)];
        for(int i = 0;i <= m;i++)
            d[i * (n + 1)] = i;
        for(int i = 0;i <= n;i++)
            d[i] = i;

        //Dynamic Programming...
        for(int i = 1; i <= m;i++){
            for(int j = 1; j <= n;j++){
                int modifyDis = d[(i - 1) * (n + 1) + (j - 1)]
                        + (srcStr.charAt(i - 1) == dstStr.charAt(j - 1) ? 0 : 1);
                int addDis = d[(i - 1) * (n + 1) + j] + 1;
                int deleteDis = d[i * (n + 1) + (j - 1)] + 1;
                d[i * (n + 1) + j] = Min(modifyDis, addDis, deleteDis);
            }
        }

        //Display Result
        System.out.println("Result Array: ");
        for(int i = 0; i <= m;i++){
            for(int j = 0; j <= n;j++){
                System.out.print("\t" + d[i * (n + 1) + j]);
            }
            System.out.println();
        }

        return d[m * (n + 1) + n];
    }

    public static int Min(int a, int b, int c){
        int result = a > b ? b : a;
        return result > c ? c : result;
    }
}
