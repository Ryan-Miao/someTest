package com.test.java;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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
@Test
    public void str_c(){
        String a = "hehe";
        String b = "he"+"he";
        String c = new String("hehe");
        String d = new String("hehe");

    System.out.println(a==b);//true
    System.out.println(a==c);//false
    System.out.println(a==a.intern());//true
    System.out.println(c==d);//false
    }

    public String change(String s, int i, StringBuffer sb, Person p){
        s="123";
        i=3;
        sb.append("woshi");
        p.setAge(100);
        sb = new StringBuffer("sbsb");
        p = new Person("bb",44);
        sb.append("123");
        return s;
    }

    @Test
    public void testChange(){
        StringBuffer sb = new StringBuffer("buff");
        String s = "aaa";
        int i = 1;
        Person p = new Person("aa",12);
        i=2;
        change(s,i,sb,p);
//        s="222";
        System.out.println(s);
        System.out.println(i);
        System.out.println(sb.toString());
        System.out.println(p);
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
    @Test
    public void str_sub(){
    	String str="0123456";
    	System.out.println(str.charAt(2));
    	System.out.println(str.substring(2));
    	System.out.println(str.substring(2,4));
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

    @Test
    public void getEncod() throws UnsupportedEncodingException {
        String sysencod = System.getProperty("file.encoding");
        System.out.println("系统默认编码："+sysencod);
        String str = "中文123abc";
        System.out.println("字符实例："+str);
        System.out.println("===============getbytes无参:===============");
        byte[] bytes = str.getBytes();
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println();
        System.out.println("通过默认字符集，将字符数组解码为字符："+new String(bytes));
        System.out.println("通过utf-8字符集，将字符数组解码为字符："+new String(bytes,"utf-8"));
        System.out.println("通过gbk字符集，将字符数组解码为字符："+new String(bytes,"gbk"));
        System.out.println("通过iso-8859-1字符集，将字符数组解码为字符："+new String(bytes,"iso-8859-1"));


        System.out.println("===============getbytes(utf-8):===============");
        byte[] bytes2 = str.getBytes("utf-8");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println();
        System.out.println("通过默认字符集，将字符数组解码为字符："+new String(bytes2));
        System.out.println("通过utf-8字符集，将字符数组解码为字符："+new String(bytes2,"utf-8"));
        System.out.println("通过gbk字符集，将字符数组解码为字符："+new String(bytes2,"gbk"));
        System.out.println("通过iso-8859-1字符集，将字符数组解码为字符："+new String(bytes2,"iso-8859-1"));
        System.out.println("===============getbytes(gbk):===============");
        byte[] bytes3 = str.getBytes("gbk");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println();
        System.out.println("通过默认字符集，将字符数组解码为字符："+new String(bytes3));
        System.out.println("通过utf-8字符集，将字符数组解码为字符："+new String(bytes3,"utf-8"));
        System.out.println("通过gbk字符集，将字符数组解码为字符："+new String(bytes3,"gbk"));
        System.out.println("通过iso-8859-1字符集，将字符数组解码为字符："+new String(bytes3,"iso-8859-1"));
        System.out.println("===============getbytes(iso-8859-1):===============");
        byte[] bytes4 = str.getBytes("iso-8859-1");
        for (int i = 0; i < bytes.length; i++) {
            System.out.print(bytes[i]);
        }
        System.out.println();
        System.out.println("通过默认字符集，将字符数组解码为字符："+new String(bytes4));
        System.out.println("通过utf-8字符集，将字符数组解码为字符："+new String(bytes4,"utf-8"));
        System.out.println("通过gbk字符集，将字符数组解码为字符："+new String(bytes4,"gbk"));
        System.out.println("通过iso-8859-1字符集，将字符数组解码为字符："+new String(bytes4,"iso-8859-1"));
    }


    @Test
    public void testSplit(){
        String str = "asdf:12:";
        str.split("2");
        String[] strarr = str.split(":",-1);
        System.out.println("arr length:"+strarr.length);
        for (int i = 0; i < strarr.length; i++) {
            System.out.println(strarr[i]);
        }

        System.out.println("==========分界线=============");
        String str2 = "";
        String[] arr = str2.split(",");
        System.out.println("数组长度："+arr.length);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println("==========结束============");
        String msg = "57:123;55:123;56:213-123;123|57:123123;55:123123;56:213123-123123;123123";
        String[] msgarr = msg.split("\\|");
        System.out.println(msgarr.length);
        for (int i = 0; i < msgarr.length; i++) {
            System.out.println(msgarr[i]);
        }
        System.out.println("=------------------------------------------------------=");
        String tarr = "";
        System.out.println(tarr.split(";").length);
        System.out.println("结果：|"+tarr.split(";")[0]+"||");
        System.out.println("=------------------------------------------------------=");
        StringBuffer sb = new StringBuffer();
        sb.append("");
        System.out.println(sb.length());
        System.out.println("=------------------------------------------------------=");
        String alerts = "mongo/alerts/contentAlerts-2017-12-01.json";
        Arrays.stream(alerts.split("/")).forEach(System.out::println);


        System.out.println(alerts.split("/")[2].substring(14,24));


    }

    /**
     * 深入理解java虚拟机
     * 2-7
     * String.intern是一个native方法，作用：如果字符创常量池中已经包含一个等于此的String对象的字符串，
     * 则返回代表池中这个字符串的String对象；否则将此String对象包含的字符串添加到 常量池中，并返回比
     * String对象的引用
     * jdk1.6及之前：常量池分配在永久代，itern将String字符串实例复制到永久代，并返回永久代中的引用
     * 1.7后：itern不复制，只在常量池中记录首次出现实例的引用
     */
    @Test
    public void testIntern(){
        String str1 = new StringBuilder("计算机").append("ruanji").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("js").append("css").toString();
        System.out.println(str2.intern() == str2);
        System.out.println(str2=="jscss");
    }

	public static void main(String[] args) {
		for (int i = 'a'; i <= 'z'; i++) {
			System.out.println(i - 96 + "====" + (char) i);
		}
	}
}

//