package com.test.http.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2015/11/16.
 */
public class ClientWithReqponseHandler {
    private static String targetUrl = "http://172.20.0.141:8041/app/login/loginByPass?tel=123&password=abc";

    public final static void main(String[] args) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpget = new HttpGet(targetUrl);
            System.out.println("Exception request " + httpget.getRequestLine());
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity!=null? EntityUtils.toString(entity):null;
                    }else{
                        throw new ClientProtocolException("Unexpected response status:"+status);
                    }
                }
            };
            String responseBody = httpclient.execute(httpget,responseHandler);
            System.out.println("------------------------------------------------");
            System.out.println(responseBody);

        } finally {
            httpclient.close();
        }
    }
}
