package com.test.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class TestMsg {

	public static String doPost(String urlpath) throws IOException {

		URL url = new URL(urlpath);
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
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
				"8859_1");
		// out.write("username=test&password=test"); // 向页面传递数据。post的关键所在！
		out.flush();
		out.close();
		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String line = null;
		StringBuilder sb = new StringBuilder();
		InputStream l_urlStream;
		l_urlStream = conn.getInputStream();
		// 传说中的三层包装阿！
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(
				l_urlStream));
		while ((line = l_reader.readLine()) != null) {
			sb.append(line + "\r\n");

		}
		l_reader.close();
		if (conn != null) {
			conn.disconnect();
			conn = null;
		}
		// System.out.println(sb.toString());
		return sb.toString();
	}

	@SuppressWarnings("unused")
	private static String sOpenUrl = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	@SuppressWarnings("unused")
	private static String sDataUrl = "http://smsapi.c123.cn/DataPlatform/DataApi";

	// 接口帐号
	private static final String account = "1001@501227700001";

	// 接口密钥
	private static final String authkey = "C6C2E9CC1724F6DC62F95DF2739C501D";

	// 通道组编号
	private static final int cgid = 52;

	// 默认使用的签名编号(未指定签名编号时传此值到服务器)
	@SuppressWarnings("unused")
	private static final int csid = 0;

	public static Document getRemain(String xmlStr) throws Exception {
		Document doc = DocumentHelper.parseText(xmlStr);
		Element root = doc.getRootElement();
		System.out.println(root.attributeValue("name"));
		System.out.println(root.attributeValue("result"));

		Element element = root.element("Item");
		System.out.println(element.getName());
		System.out.println(element.attributeValue("cid"));
		System.out.println(element.attributeValue("sid"));
		System.out.println(element.attributeValue("remain"));
		return doc;
	}

	public static String sendMsg(String mobile, String content)
			throws Exception {
		@SuppressWarnings("unused")
		String path = "http://smsapi.c123.cn/OpenPlatform/OpenApi?action=sendOnce&"
				+ "ac="
				+ account
				+ "&authkey="
				+ authkey
				+ "&cgid="
				+ cgid
				+ "&c=%E6%B5%8B%E8%AF%95" + content + "&m=" + mobile;
		String path2 = "http://smsapi.c123.cn/OpenPlatform/OpenApi?action=getBalance&"
				+ "ac=" + account + "&authkey=" + authkey;
		String xmlstr = doPost(path2);
		System.out.println(xmlstr);
		getRemain(xmlstr);

		return "";
	}
	

	public static void main(String[] args) throws Exception {
		// doPost("http://www.baidu.com");
		sendMsg("", "");
	}
}
