package com.test.http.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ClientConnectionRelease {
    private static String targetUrl = "http://172.20.0.141:8041/app/login/loginByPass?tel=123&password=abc";
    public final static void main(String[] args) throws Exception{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try{
            HttpGet httpget = new HttpGet(targetUrl);
            System.out.println("Exceting request "+httpget.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                System.out.println("-----------------------------");
                System.out.println(response.getStatusLine());
                //get hold of the response entity
                HttpEntity entity = response.getEntity();
                //If the response does not enclose an entity,there is no need
                //to bother about connection release
                if(entity!=null){
                    InputStream instream = entity.getContent();
                    try {
                        StringBuffer sb = new StringBuffer();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(instream));
                        String line =null;
                        while((line=bufferedReader.readLine())!=null){
                            sb.append(line);
                        }
                        instream.close();
                        System.out.println("===================================");
                        System.out.println(sb.toString());
                        System.out.println("===================================");
                        //do something useful with the response
                    }catch (IOException ex){
                        //In case of an IOException the connection will be released
                        //back to the connection manager automatically
                        throw ex;
                    }finally {
                        //Closing the input stream will trigger connection release
                        instream.close();
                    }
                }
            }finally {
                response.close();
            }
        }finally {
            httpclient.close();
        }
    }
}
