package com.test.http.httpclient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIUtils;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Administrator on 2015/12/1.
 */
public class http基础 {

    public void splicePara(){
        HttpGet httpGet = new HttpGet("http://www.baidu.com/s?wd=jackson&ie=UTF-8");
    }

    @Test
    public void packagePara() throws URISyntaxException {
        URI uri = URIUtils.createURI("http","www.baidu.com",80,"/s","wd=jackson&ie=UTF-8",null);
        HttpGet httpGet = new HttpGet(uri);
        System.out.println(httpGet.getURI());
    }
}
