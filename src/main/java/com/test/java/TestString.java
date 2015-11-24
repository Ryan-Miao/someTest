package com.test.java;

import java.util.Scanner;

/**
 * Created by Administrator on 2015/9/16.
 *
 */
public class TestString {

    String str1 = "hello quanjizhu";                 //若String pool中没有，则创建新对象，并放入String pool
    String str2 =str1+"haha";                       //由于string不可变类，相当于创建新对象
    String str3 = new String("hello quanjizhu");    //在heap中创建新对象

    /**
     * equals：比较两个String对象的值是否相等
     * 即：比较内容
     * 结果：true,true
     */

    public void str_equal(){
        System.out.println(str1.equals(str2));  //true
        System.out.println(str1.equals(str3));  //true
    }

    /**
     * = =：比较两个String对象的指向的内存地址是否相等。
     * 结果：false,false
     */

    public void str_c(){
        System.out.println(str1==str2);  //false
        System.out.println(str1==str3);  //false
    }

    /**
     * String 的创建
     * 1.String a = new String("aaa")   在堆上创建一个String对象
     * 2.String a = new String("aaa")   在堆上创建一个String对象
     * 3.String c = "aaa";    在String pool中查找aaa,找不到则在堆上创建一个新对象，并放入String pool
     * 4.String d = "aaa“；   d和c指向一个共同的对象
     */

    public void str_create(){
        String a = new String("aaa");
        String b = new String("aaa");
        String c = "aaa";
        String d = "aaa";
        System.out.println(a==b);   //false
        System.out.println(a.equals(b));  //true
        System.out.println(a==c);   //false
        System.out.println(a.equals(c));  //true
        System.out.println(c==d);   //true
        System.out.println(c.equals(d));  //true

        a=a.intern();//将a的内容放到String pool,如果pool中存在，则直接返回pool中的地址，如果不存在则放入，本测试用例中a的地址最终变为c的地址
        System.out.println(a==c);   //true
        System.out.println(a.equals(c)); //true
    }
    
    /**
     * 
    * @Title: str_sub 
    * @Description:substring(start)包含起点start
    * 				substring(start,end)包含start到end-1 
    * @param
    * @return void    返回类型
     */

    public void str_sub(){
    	String str="unhappy";
    	System.out.println(str.charAt(2));
    	System.out.println(str.substring(2));
    	System.out.println(str.substring(2,str.length()));
    }
    
    /**
     * 
    * @Title: str_index 
    * @Description: indexOf("str") str首字母s第一次出现的位置
    * @param
    * @return void    返回类型
     */

    public void str_index(){
    	String str="addfoo123oo456oo789";
    	int start = str.indexOf("oo");
    	System.out.println(start);
    	int endStrStart = start+"oo".length();
    	//为了通过某标识oo将字符串分成两段
    	System.out.println(str.substring(endStrStart));
    }
    



	public void charToInt() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("请输入：");
			String str = sc.next();
			char[] cs = str.toCharArray();
			for (char c : cs) {
				System.out.print((int) c - 96);
			}
			System.out.println();
			
			sc.close();
		}

	}


	public static void main(String[] args) {
		for (int i = 'a'; i <= 'z'; i++) {
			System.out.println(i - 96 + "====" + (char) i);
		}
	}
}
