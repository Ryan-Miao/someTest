package com.test.http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Testpost {
	
	public static void TestPost() throws IOException {
		
		URL url = new URL("http://localhost:8080/back/login/login");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setDoOutput(true);
		conn.setConnectTimeout(5000);
		conn.setReadTimeout(30000);
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setUseCaches(false);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Connection", "Keep-Alive");
		conn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "8859_1");
		out.write("username=test&password=test"); // 向页面传递数据。post的关键所在！
		out.flush();
		out.close();
		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String line=null;
		StringBuilder sb=new StringBuilder();
		InputStream l_urlStream;
		l_urlStream = conn.getInputStream();
		// 传说中的三层包装阿！
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(
				l_urlStream));
		while ((line = l_reader.readLine()) != null) {
			sb.append(line+"\r\n");

		}
		l_reader.close();
		if(conn!=null){
			conn.disconnect();
			conn=null;
		}
		System.out.println(sb.toString());
		
	}
	

	public static void main(String[] args) throws IOException {
		TestPost();
	}
}

