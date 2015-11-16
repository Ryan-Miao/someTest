package com.test.util;

import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;



public class TokenUtil {

	private static final int INTERVAL = 7;// token过期时间间隔 天
	private static final String YAN = "hlaqpp/dkjsfkljdf((DFkfKJ(378639337fsjkdfhlkiweruio";// 加盐
	private static final int HOUR = 3;// 检查token过期线程执行时间 时
	
	private static Logger logger = Logger.getLogger("visit");

	private static Map<Integer, Token> tokenMap = new HashMap<Integer, Token>();
	private static TokenUtil tokenUtil = null;
	static ScheduledExecutorService scheduler =Executors.newSingleThreadScheduledExecutor(); 

	static {
		logger.info("\n===============进入TokenUtil静态代码块==================");
		listenTask();
	}
	

	public static TokenUtil getTokenUtil() {
		if (tokenUtil == null) {
			synInit();
		}

		return tokenUtil;
	}

	private static synchronized void synInit() {
		if (tokenUtil == null) {
			tokenUtil = new TokenUtil();
		}
	}

	public TokenUtil() {
	}
	
	

	public static Map<Integer, Token> getTokenMap() {
		return tokenMap;
	}

	/**
	 * 产生一个token
	 */
	public static Token generateToken(String uniq,int id) {
		Token token = new Token(MD5(System.currentTimeMillis()+YAN+uniq+id), System.currentTimeMillis());
		synchronized (tokenMap) {
			tokenMap.put(id, token);
		}
		return token;
	}


	/**
	 * @Title: removeToken
	 * @Description: 去除token
	 * @param @param nonce
	 * @param @return 参数
	 * @return boolean 返回类型
	 */
	public static boolean removeToken(int id) {
		synchronized (tokenMap) {
			tokenMap.remove(id);
			logger.info(tokenMap.get(id) == null ? "\n=========已注销========": "\n++++++++注销失败+++++++++++++++");
		}
		return true;
	}

	/**
	 * @Title: volidateToken
	 * @Description: 校验token
	 * @param @param signature
	 * @param @param nonce
	 * @param @return 参数
	 * @return boolean 返回类型
	 */
	public static boolean volidateToken(String signature, int id) {
		boolean flag = false;
		Token token = (Token) tokenMap.get(id);
		if (token != null && token.getSignature().equals(signature)) {
			logger.info("\n=====已在线=======");
			flag = true;
		}

		return flag;
	}
	
	/**
	 * 
	 * @Title: MD5
	 * @Description: 加密
	 * @param @param s
	 * @param @return 参数
	 * @return String 返回类型
	 */
	public final static String MD5(String s) {
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			return byte2hex(mdInst.digest());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字节数组转换成16进制字符串
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
	
	/**
	* @Title: listenTask 
	* @Description: 定时执行token过期清除任务
	* @param     参数
	* @return void    返回类型
	 */
	public static void listenTask(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //定制每天的HOUR点，从明天开始
        calendar.set(year, month, day+1, HOUR, 0, 0);
//        calendar.set(year, month, day, 17, 11, 40);
        Date date = calendar.getTime();
        
        scheduler.scheduleAtFixedRate( new ListenToken(), (date.getTime()-System.currentTimeMillis())/1000, 60*60*24, TimeUnit.SECONDS);
	}
	
	

	/**
	 * @ClassName: ListenToken
	 * @Description: 监听token过期线程runnable实现
	 * @author mrf
	 * @date 2015-10-21 下午02:22:24
	 * 
	 */
	static class ListenToken implements Runnable {
		public ListenToken() {
			super();
		}

		public void run() {
			logger.info("\n**************************执行监听token列表****************************");
			try {
				synchronized (tokenMap) {
					for (int i = 0; i < 5; i++) {
						if (tokenMap != null && !tokenMap.isEmpty()) {
							for (Entry<Integer, Token> entry : tokenMap.entrySet()) {
								Token token = (Token) entry.getValue();
								logger.info("\n==============已登录用户有："+entry + "=====================");
//							try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
								int interval = (int) ((System.currentTimeMillis() - token.getTimestamp()) / 1000 / 60 / 60 / 24);
								if (interval > INTERVAL) {
									tokenMap.remove(entry.getKey());
									logger.info("\n==============移除token：" + entry+ "=====================");
								}

							}
						}
					}
					
				}
			} catch (Exception e) {
				logger.error("token监听线程错误："+e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	 

	public static void main(String[] args) {
		System.out.println(generateToken( "s",1));
		System.out.println(generateToken( "q",1));
		System.out.println(generateToken( "s3",2));
		System.out.println(generateToken( "s4",3));
		System.out.println(removeToken(3));
		System.out.println(getTokenMap());
	}

}

 
