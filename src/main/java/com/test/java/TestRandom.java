package com.test.java;

import java.util.Random;

/**
* @ClassName: TestRandom 
* @Description: 测试随机数
* @author mrf
* @date 2015-11-11 下午08:11:20 
*
 */
public class TestRandom {
	
	public static  TestRandom tr = null;
	private TestRandom(){}
	public static TestRandom getInstance(){
		if(tr==null){
			return init();
		}
		
		return tr;
	}
	
	private static synchronized TestRandom init(){
		return new TestRandom();
	}
	
	/**
	* @Title: ZeroNine 
	* @Description:随机生成0-9 
	* <p>Math.random()生成  0.0-0.9999999...</p>
	* @param
	* @return void    返回类型
	 */

	public void zeroNine(){
		int n = (int)(Math.random()*10);
		System.out.println(n);
	}
	
	/**
	* @Title: zeroNine2 
	* @Description: 随机生成0-9
	* <p>new Random().nextInt(n) 生成     0-(n-0.000000000...0001)</p>
	* @param
	* @return void    返回类型
	 */
	
	public void zeroNine2(){
		Random random = new Random();
		int nextInt = random.nextInt(10);
		System.out.println(nextInt);
	}
	
	
	public static void main(String[] args) {
		for (int i = 0; i < 20; i++) {
//			getInstance().zeroNine();
			getInstance().zeroNine2();
		}
	}

}
