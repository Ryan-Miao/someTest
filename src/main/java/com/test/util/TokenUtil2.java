package com.test.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class TokenUtil2 {

	private static final String TOKEN_MAP_NAME = "tokenMap";
	public static final String TOKEN_STRING_NAME = "token";

	@SuppressWarnings("unchecked")
	/**
	 * 从session中获取tokenList,没有则创建
	 */
	private static Map<String, String> getTokenList(HttpSession session) {
		Object obj = session.getAttribute(TOKEN_MAP_NAME);
		if (obj != null) {
			return (Map<String, String>) obj;
		} else {
			Map<String, String> tokenList = new HashMap<String, String>();
			session.setAttribute(TOKEN_MAP_NAME, tokenList);
			return tokenList;
		}
	}

	/**
	 * 
	* @Title: saveTokenString 
	* @Description: 将token放入session中的token列表
	* @param @param tokenStr
	* @param @param session    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private static void saveTokenString(String tokenStr,String timestamp, HttpSession session) {
	    Map<String, String> tokenList = getTokenList(session);
		Map<String, String> map = new HashMap<String, String>();
		map.put(timestamp, tokenStr);
		session.setAttribute(TOKEN_MAP_NAME, tokenList);
	}

	/**
	 * 
	* @Title: generateTokenString 
	* @Description: 生成token
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	private static String generateTokenString() {
		return new Long(System.currentTimeMillis()).toString();
	}

	/**
	 * Generate a token string, and save the string in session, then return the token string.
	 * 产生并获取一个token
	 * @param HttpSession session
	 * @return a token string used for enforcing a single request for a particular transaction.
	 */
	public static String getTokenString(HttpSession session,String original) {
		String tokenStr = generateTokenString();
		saveTokenString(tokenStr,original, session);
		return tokenStr;
	}

	/**
	 * check whether token string is valid. if session contains the token
	 * string, return true.
	 * otherwise, return false.
	 * @param String tokenStr
	 * @param HttpSession  session
	 * @return true: session contains tokenStr; false: session is null or
	 *         tokenStr is id not in session
	 */
	public static boolean isTokenStringValid(String tokenStr,String timestamp, HttpSession session) {
		boolean valid = false;
		if (session != null) {
			Map<String, String> tokenMap = getTokenList(session);
			String str = tokenMap.get(timestamp);
			if(str!=null && str.equals(tokenStr)){
				valid = true;
				tokenMap.remove(timestamp);
			}
		}
		return valid;
	}

}
