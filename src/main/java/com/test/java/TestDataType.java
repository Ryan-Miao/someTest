package com.test.java;


import org.junit.Test;

import java.util.Date;

/**
 * Created by Administrator on 2015/9/16.
 * 测试java的数据类型
 */
public class TestDataType {

    /**
     * null+1=?
     */
    public void nullAddNum(){
        //null不是整形，不能相加，晕死
//        System.out.println(1 + Integer.parseInt(null));
    }

	/**
	 * 测试switch
	 */
	@Test
	public void testSwitch(){
		String a = "aa";
		int b = 12;
		char c = '2';
		byte d = 1;
		float e = 1.0f;
		switch (a){

		}
		switch (b){

		}
		switch (c){

		}
		switch (d){

		}
//		switch (e){
//
//		}
	}

    /**
     * char转ascii
     */

    public void char_ASCII(){
        char a = 'a';
        char b = 78;
        System.out.println((int)a+"==="+b);

        System.out.println("========================================");
        for (int i = 65; i < 123; i++) {
            if(i%15==0)
                System.out.println();
            System.out.print(i+"===="+(char)i+"  ");
        }
    }
    
    /**
     * 
    * @Title: currentTimeMillis 
    * @Description: 测试时间类型毫秒
    * @param
    * @return void    返回1970后经过的当前的毫秒
    * @throws
     */

    public void currentTimeMillisTest(){
    	long currentTimeMillis = System.currentTimeMillis();
    	@SuppressWarnings("deprecation")
		Date date = new Date(115,9,10);//20151020
    	long old = date.getTime();
    	System.out.println(old);
    	System.out.println(currentTimeMillis);
    	System.out.println((currentTimeMillis-old)/1000/60/60/24);
    	System.out.println(new Date(currentTimeMillis));
    }
    

    public void byteTest(){
    	String str1 = "12345";
    	String str2 = "123456";
    	String str3 = "1234";
    	String str4 = "123";
    	System.out.println(byte2hex(str1.getBytes()));
    	System.out.println(byte2hex(str2.getBytes()));
    	System.out.println(byte2hex(str3.getBytes()));
    	System.out.println(byte2hex(str4.getBytes()));
    }
    
    /**
    * @Title: doubleToInt 
    * @Description: 双精度强转为int会舍弃小数
    * @param
    * @return void    返回类型
     */

    public void doubleToInt(){
    	System.out.println(1.2);
    	System.out.println((int)1.2);
    	System.out.println((int)1.4);
    	System.out.println((int)1.5);
    	System.out.println((int)1.6);
    	System.out.println((int)1.7);
    	System.out.println((int)2.7);
    }


	@Test
	public void testByte(){
		Byte b = 12;
		System.out.println(b);
		System.out.println((int)b);
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
}
