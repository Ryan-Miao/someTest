package com.test.util;



import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.activation.MimetypesFileTypeMap;

import com.jfinal.kit.PropKit;


public class PicUtil {
	
	private static String Site_PATH;
	
	static{
		Site_PATH = PropKit.get("picUrl");
	}

	/**
	 * 
	 * @param folder	图片存放的文件夹的名字，不传入此参数默认放入default文件夹下
	 * @param f			文件
	 * @return
	 */
	public static String uploadPic(UploadFolder folder,File f){
		
		String urlStr =Site_PATH+"/uploadPic?fname="+folder.getValue();
		Map<String, String> textMap = new HashMap<String, String>();
		textMap.put("filename", "filename");

		Map<String, File> fileMap = new HashMap<String, File>();
		fileMap.put("userfile", f);

		String res = "";
		HttpURLConnection conn = null;
		String BOUNDARY = "---------------------------123821742118716"; // boundary就是request头和上传文件内容的分隔符
		try {
			URL url = new URL(urlStr);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(30000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + BOUNDARY);

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text

			// file
			if (fileMap != null) {
				Iterator<Entry<String, File>> iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Entry<String, File> entry =  iter.next();
					String inputName = (String) entry.getKey();
					File file = (File) entry.getValue();
					if (file == null) {
						continue;
					}
					String filename = file.getName();
					String contentType = new MimetypesFileTypeMap()
							.getContentType(file);
					if (filename.endsWith(".png")) {
						contentType = "image/png";
					}
					if (contentType == null || contentType.equals("")) {
						contentType = "application/octet-stream";
					}

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY)
							.append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\""
							+ inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(
							new FileInputStream(file));
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();

			// 读取返回数据
			StringBuffer strBuf = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				strBuf.append(line).append("\n");
			}
			res = strBuf.toString();
			reader.close();
			reader = null;
		} catch (Exception e) {
			System.out.println("发送POST请求出错。" + urlStr);
			res = "1";//表示出错
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
				conn = null;
			}
		}
		return res;
	}

	public static void delPic(String path) {
		if (path != null && path.length() > 0) {
			try {
				URL url = new URL(Site_PATH + "/delPic?path=" + path);
				HttpURLConnection uc = (HttpURLConnection) url.openConnection();
				uc.getResponseCode();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	enum UploadFolder {
		IMG("weddingimg"){//值就是value.通过私有构造函数产生
			public boolean isRest(){
				return true;
			}
		},ARTICLE("artcile"){
			public boolean isRest(){
				return true;
			}
		},TEST("test"){
		public boolean isRest(){
			return true;
		}
	};
		
		private String value;
		
		private UploadFolder(String value){
			this.value=value;
		}
		
		public String getValue(){
			return value;
		}
		
		public boolean isRest(){
			return false;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		// File f=new File("D://d.jpg");
		// if(!"".equals(uploadPic(EnterFolder.GOODS,f,1))) {
		// System.out.println("success"+uploadPic(EnterFolder.GOODS,f,400,400,200,200,50,50,true,1));
		// System.out.println("success"+uploadPic(EnterFolder.GOODS,f,1));
		// }
		delPic("enterUpload/1/goods/1411356528382866.jpg");

	}

}
