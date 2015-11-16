package com.test.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
* @ClassName: TestHttpClient 
* @Description:学习使用HttpClient 
* @author mrf
* @date 2015-11-13 下午07:54:18 
*
 */
public class TestHttpClient {

	private static String targetUrl="http://api.dangqian.com/apidiqu2/api.asp?format=json&callback=wjr";
	
	public static void testGet(String targetUrl) throws ClientProtocolException, IOException{
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(targetUrl);
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		// The underlying HTTP connection is still held by the response object
		// to allow the response content to be streamed directly from the network socket.
		// In order to ensure correct deallocation of system resources
		// the user MUST call CloseableHttpResponse#close() from a finally clause.
		// Please note that if response content is not fully consumed the underlying
		// connection cannot be safely re-used and will be shut down and discarded
		// by the connection manager. 
		try {
			System.out.println("==============================================================================================");
		    System.out.println(response1.getStatusLine());
		    System.out.println("==============================================================================================");
		    HttpEntity entity1 = response1.getEntity();
		    // do something useful with the response body
		    // and ensure it is fully consumed
		    
		    EntityUtils.consume(entity1);
		    InputStream content = entity1.getContent();
		    
		} finally {
		    response1.close();
		}

	}
	
	public static void testPost(String targetUrl) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(targetUrl);
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("username", "vip"));
		nvps.add(new BasicNameValuePair("password", "secret"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));
		CloseableHttpResponse response2 = httpclient.execute(httpPost);

		try {
		    System.out.println(response2.getStatusLine());
		    HttpEntity entity2 = response2.getEntity();
		    // do something useful with the response body
		    System.out.println(response2.getEntity());
		    // and ensure it is fully consumed
		    EntityUtils.consume(entity2);
		} finally {
		    response2.close();
		}
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException  {
//		testGet(targetUrl);
//		testPost(targetUrl);
		Content content;
				content = Request.Get(targetUrl+"&id=130000000000")
	    .execute().returnContent();
		
//		content = Request.Post(targetUrl)
//	    .bodyForm(Form.form().add("id",  "130000000000").add("password",  "abc").build())
//	    .execute().returnContent();
		System.out.println(content.asString());
	}
	

}
