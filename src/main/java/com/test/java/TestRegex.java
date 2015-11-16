package com.test.java;

public class TestRegex {
	
	/**
	 * 测试或出现1次或0次
	* @Title: testOr 
	* @Description: 
	* ?
	   零次或一次匹配前面的字符或子表达式。例如，“do(es)?”匹配“do”或“does”中的“do”。? 等效于 {0,1}。
	* @return void    返回类型
	 */

	public void testOr(){
		String str="123,456,789,123,qwe,rty,123";
		System.out.println(str);
		String regex="123(,)?";
		String string = str.replaceFirst(regex, "￥");
		String string2 = str.replaceAll(regex, "￥");
		System.out.println(string);
		System.out.println(string2);
		
		System.out.println(str.replaceAll("(123(,)?)|((,)?123)", "￥"));
		
		String imgele="\\upload\\webHome\\weddingimg\\45199b90-d1b4-4a06-b860-f97fb730d749.jpg";
		String img = "\\upload\\webHome\\weddingimg\\45199b90-d1b4-4a06-b860-f97fb730d749.jpg," +
				"\\upload\\webHome\\weddingimg\\c9bd4211-4dba-4063-bbf2-861c45c3aa83.jpg";
//		System.out.println("("+imgele+"(,)?)|((,)?"+imgele+")");
//		regex="("+imgele+"(,)?)|((,)?"+imgele+")";
//		System.out.println(img.replaceAll(imgele, ""));
		String[] arr = img.split(",");
		StringBuilder sb = new StringBuilder();
		System.out.println(arr.length);
		for (int i = 0; i < arr.length; i++) {
			if(!imgele.equals(arr[i])){
				sb.append(arr[i]);
			}
		}
		System.out.println(sb.toString());
	}

}
