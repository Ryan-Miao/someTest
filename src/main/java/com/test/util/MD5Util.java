package com.test.util;

import java.security.MessageDigest;

public class MD5Util {
	public final static String MD5(String s) {
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			return byte2hex(md);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	* @Title: MD5 
	* @Description: update两次相当于原字符串拼接后加密
	* @param @param s
	* @param @param s2
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public final static String MD5(String s,String s2) {
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			mdInst.update(s2.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			return byte2hex(md);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字节数组转换成16进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		StringBuilder sbDes = new StringBuilder();
		String tmp = null;
		for (int i = 0; i < b.length; i++) {
			tmp = (Integer.toHexString(b[i] & 0xFF));
			if (tmp.length() == 1) {
				sbDes.append("0");
			}
			sbDes.append(tmp);
		}
		return sbDes.toString();
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.MD5("123"));
		System.out.println(MD5Util.MD5("456"));
		System.out.println(MD5Util.MD5("123456"));
		System.out.println(MD5Util.MD5("123","456"));
	}
}
