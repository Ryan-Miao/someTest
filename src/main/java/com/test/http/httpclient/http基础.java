package com.test.http.httpclient;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2015/12/1.
 */
public class http基础 {

    private static HttpClient httpclient = HttpClients.createDefault();

    /**
     * 参数拼接
     *
     * @throws URISyntaxException
     */
    @Test
    public void packagePara() throws URISyntaxException {
//        HttpGet httpGet = new HttpGet("http://www.baidu.com/s?wd=jackson&ie=UTF-8");
        URI uri = URIUtils.createURI("http", "www.baidu.com", 80, "/s", "wd=jackson&ie=UTF-8", null);
        HttpGet httpGet = new HttpGet(uri);
        System.out.println(httpGet.getURI());
    }

    /**
     * 拼接参数
     *
     * @throws URISyntaxException
     */
    @Test
    public void packageParas() throws URISyntaxException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("wd", "testvalue"));
        params.add(new BasicNameValuePair("ie", "utf-8"));
        URI uri = URIUtils.createURI("http", "www.baidu.com", 80, "/s", URLEncodedUtils.format(params, "utf-8"), null);
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());
    }

    /**
     * 解析响应
     */
    @Test
    public void resolveResponse() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "ok");
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().toString());
    }

    /**
     * 处理报文头部
     */
    @Test
    public void HeadHandler() {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        response.addHeader("Set-Cookie", "c1=a;path=/;domain=localhost");
        response.addHeader("Set-Cookie", "c2=b;path=\"/\",c3=c;domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1);
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2);
        Header[] headers = response.getHeaders("Set-Cookie");
        System.out.println(headers.length);
        //获取所有头部信息
        HeaderIterator itr = response.headerIterator("Set-Cookie");
        System.out.println("所有的元素：");
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        //获取独立元素
        HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
        System.out.println("独立获取元素：");
        while (it.hasNext()) {
            HeaderElement ele = it.nextElement();
            System.out.println(ele.getName() + "=" + ele.getValue());
            NameValuePair[] parameters = ele.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.println(" " + parameters[i]);
            }
        }


    }

    /**
     * 实体
     */
    @Test
    public void testEntity() throws IOException {
        StringEntity entity = new StringEntity("important message", "utf-8");
        System.out.println(entity.getContentType());
        System.out.println(entity.getContentLength());
        System.out.println(EntityUtils.getContentCharSet(entity));
        System.out.println(EntityUtils.toString(entity));
        System.out.println(EntityUtils.toByteArray(entity).length);
    }

    /**
     * 释放
     * HttpEntity#consumeContent()可以多次调用
     * 若仅仅需要一部分，可以使用abort终止
     */
    @Test
    public void testRelease() throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:8080/");
        HttpResponse res = httpclient.execute(httpGet);
        HttpEntity entity = res.getEntity();
        if (entity != null) {
            InputStream inputStream = entity.getContent();
//            byte [] b = new byte[10240];
//            int read = inputStream.read(b);
            int read2 = inputStream.read();
//            System.out.println(new String(b));
            System.out.println(read2);
            //do not need the rest
            httpGet.abort();
        }
    }

    /**
     * 消耗实体的方法
     */
    @Test
    public void consumeEnty() throws IOException {
        HttpGet httpGet = new HttpGet("http://localhost:8080/");
        HttpResponse res = httpclient.execute(httpGet);
        HttpEntity entity = res.getEntity();
        if (entity != null) {
            long len = entity.getContentLength();
            System.out.println(len);
            if (len != -1 && len < 20480) {
                System.out.println(EntityUtils.toString(entity));
            } else {
                //stream content out
                System.out.println(EntityUtils.toString(entity));
            }
        }
        System.out.println("======================================");
        System.out.println("======================================");
        System.out.println("======================================");
        System.out.println("======================================");
        System.out.println("======================================");
        //java.io.IOException: Attempted read from closed stream.
//        System.out.println(EntityUtils.toString(entity));

    }

    /**
     * 缓存多次使用实体
     */
    @Test
    public void bufferEnty() throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:8080/");
        CloseableHttpResponse res = httpclient.execute(httpGet);
        HttpEntity entity = res.getEntity();
        if (entity != null) {
            entity = new BufferedHttpEntity(entity);
            System.out.println(EntityUtils.toString(entity));
        }
        //可以多次使用了
        System.out.println(EntityUtils.toString(entity));
    }

    /**
     * 生成实体内容
     */
    @Test
    public void generateEntity() {
        File file = new File("123.txt");
        FileEntity entity = new FileEntity(file, "text/plain;charset=\"utf-8\"");
        HttpPost httpPost = new HttpPost("http://localhost:8080");
        httpPost.setEntity(entity);
    }

    /**
     * 动态生成实体
     */
    @Test
    public void DynamicPro() throws IOException {
        ContentProducer cp = new ContentProducer() {
            @Override
            public void writeTo(OutputStream outstream) throws IOException {
                Writer writer = new OutputStreamWriter(outstream, "utf-8");
                writer.write("<response>");
                writer.write("<content>");
                writer.write("importent stuff");
                writer.write("</content>");
                writer.write("</response>");
                writer.flush();
            }

        };

        HttpEntity entity = new EntityTemplate(cp);
        HttpPost httpPost = new HttpPost("http://localhost:8080");
        httpPost.setEntity(entity);

        System.out.println(EntityUtils.toString(entity));
    }

    /**
     * HTML表单
     */
    @Test
    public void htmlForm() throws IOException {
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("tel", "123"));
        formparams.add(new BasicNameValuePair("password", "abc"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "utf-8");
        HttpPost httpPost = new HttpPost("http://localhost:8080/app/login/loginByPass");
//        HttpPost httpPost = new HttpPost("http://localhost:8080");
        httpPost.setEntity(entity);
        HttpResponse response = httpclient.execute(httpPost);
        System.out.println(response.getProtocolVersion());
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getStatusLine().toString());

        System.out.println(response.toString());

        HttpEntity entity1 = response.getEntity();
        if (entity1 != null) {
            InputStream inputStream = entity1.getContent();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb = new StringBuffer();
            String readline = null;
            while ((readline = bufferedReader.readLine()) != null) {
                sb.append(readline);
            }
            inputStream.close();
            System.out.println(sb.toString());
        }
    }

    /**
     * 响应控制器
     */
    @Test
    public void responseHand() throws IOException {
        HttpPost httpPost = new HttpPost("http://localhost:8080/app/login/loginByPass");
        ResponseHandler<byte[]> handler = new ResponseHandler<byte[]>() {
            @Override
            public byte[] handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toByteArray(entity);
                } else {
                    return null;
                }

            }
        };
        List<NameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("tel", "123"));
        formparams.add(new BasicNameValuePair("password", "abc"));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "utf-8");
        httpPost.setEntity(entity);
        byte[] response = httpclient.execute(httpPost, handler);
        System.out.println(new String(response));
    }

    /**
     * 执行环境
     */
    @Test
    public void exeContext() throws IOException {
        HttpContext localContext = new BasicHttpContext();
        HttpPost httpPost = new HttpPost("http://localhost:8080/app/login/loginByPass");
        HttpResponse res = httpclient.execute(httpPost, localContext);
        HttpHost target = (HttpHost) localContext.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
        System.out.println("final target:" + target);
        HttpEntity entity = res.getEntity();
        if (entity != null) {
            EntityUtils.consume(entity);
        }

    }

    /**
     * 请求重试
     */
    @Test
    public void retry() {
        HttpPost httpPost = new HttpPost("http://localhost:8080/app/login/loginByPass");
        HttpRequestRetryHandler myRetryhandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 5) {
                    //如果超过最大重试次数
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {
                    //如果服务器丢掉了连接，重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {
                    //不重试ssl握手异常
                    return false;
                }
                HttpRequest request = (HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQ_SENT);
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    //如果是幂等的
                    return true;
                }
                return false;
            }
        };
    }

    /**
     * 拦截器
     */
    public void interceptor() {
        HttpClientContext localContext = HttpClientContext.create();
        AtomicInteger count = new AtomicInteger(1);
        localContext.setAttribute("count", count);

    }

    /**
     * 测试ssl
     * 环信token获取
     */
    @Test
    public void httppara() throws IOException {
        String target = "http://a1.easemob.com/easemob-playground/test1/token";

        List<NameValuePair> headers = new ArrayList<>();
        headers.add(new BasicNameValuePair("Content-Type", "application/json"));

        JsonNodeFactory factory = new JsonNodeFactory(false);
        ObjectNode objectNode = factory.objectNode();
        objectNode.put("grant_type", "client_credentials");
        objectNode.put("client_id", "YXA6wDs-MARqEeSO0VcBzaqg5A");
        objectNode.put("client_secret", "YXA6JOMWlLap_YbI_ucz77j-4-mI0JA");

        HttpPost httpPost = new HttpPost(target);
        for (NameValuePair header : headers) {
            httpPost.addHeader(header.getName(), header.getValue());
        }
        httpPost.setEntity(new StringEntity(objectNode.toString(), "utf-8"));

        httpclient = getClient(true);

        HttpResponse response = httpclient.execute(httpPost);
        System.out.println(response.toString());
        HttpEntity entity1 = response.getEntity();
        System.out.println(EntityUtils.toString(entity1, "utf-8"));

    }

    public static HttpClient getClient(boolean isSSL) {
        if (isSSL) {
            try {
                SSLContext sslContext = new SSLContextBuilder()
                        .loadTrustMaterial(new TrustSelfSignedStrategy()).build();
                SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                        sslContext);
                return HttpClients.custom().setSSLSocketFactory(sslsf).build();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }

        return httpclient;
    }


    //test https ignore certification
    @Test
    public void testHttps() throws Exception {
        String target = "https://localhost:8443/hello/list";
        HttpPost post = new HttpPost(target);
        httpclient = getClient(true);
        HttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        System.out.println(result);
    }

    //test https with a certain certification
    @Test
    public void testHttpsWithCertification() throws Exception {
        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(new File("my.keystore"), "123456".toCharArray(),
                        new TrustSelfSignedStrategy())
                .build();

        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .build();
        try {

            HttpGet httpget = new HttpGet("https://localhost:8443/hello/list");

            System.out.println("Executing request " + httpget.getRequestLine());

            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();

                System.out.println("----------------------------------------");
                System.out.println(response.getStatusLine());
                System.out.println(EntityUtils.toString(entity));
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }


}






