package com.test.java;

/**
* @ClassName: TestPointer 
* @Description: 测试指针传址传值问题
* @author mrf
* @date 2015-11-20 下午06:39:17 
*
 */
public class TestPointer {
	
	/**
	* @Title: change 
	* @Description: 变量为基本类型，只有数值，传递的就是数值
	* @param @param sql    参数
	* @return void    返回类型
	 */
	public static void change(String sql){
		sql = "456";
	}
	
	/**
	* @Title: change 
	* @Description: 变量为对象类型，传递地址
	* @param @param sql    参数
	* @return void    返回类型
	 */
	public static void change(StringBuilder sql){
		sql.append("456");
	}
	
	public static void changeRef(StringBuilder sql){
		sql = new StringBuilder("aaa");
	}
	
	public static void change(Integer n){
		n=456;
	}
	
	public static void main(String[] args) {
		String sql = "123";
		change(sql);
		System.out.println(sql);
		
		StringBuilder sb = new StringBuilder();
		sb.append("123");
		//引用传递给sb2，sb2和sb将指向共同的数据
		StringBuilder sb2 = new StringBuilder();
		sb2 = sb;
		System.out.println("sb2"+sb2.toString());
		//引用传递，可以修改引用指向的数值，数值改变，则所有引用代表的对象的值都改变
		change(sb);
		System.out.println("sb ="+sb.toString());
		System.out.println("sb2="+sb2.toString());
		
		//若传递给引用，但修改引用的指向，原来引用代表的对象并没消失，指向的数值依然还是原来的数值
		//传递引用就是相当于指针，告诉别人我家的位置在哪里，参数仅仅代表一个写字的纸条，别人如果根据纸条去找到你家，偷了你家的东西，那你家真被偷了。
		//若别人直接把另一个人家偷了，和你没关系。再次强调：传对象就是传对象的引用，就是一个指针，只能告诉别人你指向的方向，而不能对这个指针做任何改变。
		//换个说法：传对象，到了所执行的方法体内，这个对象和原来的对象仅仅是指向相同，而完全可以看成新的对象
		changeRef(sb);
		System.out.println("修改引用后==================");
		System.out.println("sb ="+sb.toString());
		System.out.println("sb2="+sb2.toString());
		
		
		
		
		int n = 123;
		change(n);
		System.out.println(n);
		
		Integer nn = 123;
		change(nn);
		System.out.println(nn);
	}

}
