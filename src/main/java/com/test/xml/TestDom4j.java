package com.test.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
* @ClassName: TestDom4j 
* @Description:dom4j练习xml的读写 
* @author mrf
* @date 2015-11-11 下午02:25:20 
*
 */
public class TestDom4j {
	
	/**
	* @Title: read 
	* @Description:读取xml文件 
	* @param @param path    参数
	* @return void    返回类型
	 * @throws DocumentException 
	 */
	public static void read(String path) throws DocumentException{
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(new File(path));
		//获取根元素
		Element root = doc.getRootElement();
		System.out.println(root.getName()+root.attributeValue("name"));
		//获取子元素
		@SuppressWarnings("unchecked")
		List<Element> grades = root.elements();
		if(!grades.isEmpty()){
			for (int i = 0; i < grades.size(); i++) {
				Element element = grades.get(i);
				System.out.println(element.getName()+element.attributeValue("name"));
				@SuppressWarnings("unchecked")
				List<Element> classes = element.elements();
				if(classes.size()>0){
					for (int j = 0; j < classes.size(); j++) {
						Element cls = classes.get(j);
						@SuppressWarnings("unchecked")
						List<Element> stus = cls.elements();
						if(!stus.isEmpty()){
							for (int k = 0; k < stus.size(); k++) {
								Element stu = stus.get(k);
								System.out.println(stu.getName()+stu.attributeValue("name"));
								
								//以下证明elements方法返回值不会为null,另查看源码发现也是直接创建list
//								List list = stu.elements();
//								System.out.println(list==null);
//								System.out.println(list.size());
//								System.out.println(list);
							}
						}
						
					}
				}
			}
		}
	}
	
	/**
	* @Title: getRandoStr 
	* @Description:获取一个随机字符串 
	* @param @param len
	* @param @return    参数
	* @return String    返回类型
	 */
	public static String getRandoStr(int len){
		String base = "abcdefghijklmnopqrstuvwxyz1234567890";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			sb.append(base.charAt(random.nextInt(base.length())));
		}
		
		return sb.toString();
	}
	
	/**
	* @Title: write 
	* @Description: 测试写一个school文件
	* @param @throws IOException    参数
	* @return void    返回类型
	 */
	public static void write(String path) throws IOException{
		//创建doc文档
		Document doc = DocumentHelper.createDocument();
		Element root = DocumentHelper.createElement("school");
	    //设置根元素
		doc.setRootElement(root);
		//添加属性
		root.addAttribute("name","实验中学");
		root.addAttribute("position","河北石家庄");
		
		Element grade = root.addElement("grade");
		grade.addAttribute("name", "年级");
		grade.addAttribute("size", "1");
		Element cls = grade.addElement("class");
		cls.addAttribute("name", "班级");
		cls.addAttribute("size", "70");
		Element stu = cls.addElement("student");
		stu.addAttribute("name", getRandoStr(4));
		stu.addAttribute("age", 13+"");
		stu.addAttribute("sex", new Random().nextInt(2)+"");
		stu.setText("学生");
		
//		//添加三个子节点初一初二初三
//		for (int i = 1; i < 4; i++) {
//			Element grade = root.addElement("grade");
//			grade.addAttribute("name", "grade_"+i);
//			grade.addAttribute("size", "20");
//			//添加20个班级
//			for (int j = 1; j < 21; j++) {
//				Element cls = grade.addElement("class");
//				cls.addAttribute("name", "grade_"+i+"_class_"+j);
//				cls.addAttribute("size", "70");
//				//添加70个学生
//				for (int k = 1; k < 71; k++) {
//					Element stu = cls.addElement("student");
//					stu.addAttribute("name", getRandoStr(4));
//					stu.addAttribute("age", 12+i+"");
//					stu.addAttribute("sex", new Random().nextInt(2)+"");
//					stu.setText("学生");
//				}
//			}
//		}
		
		
		//输出
		//控制台
		new XMLWriter().write(doc);
		
		//文件
		//格式 -缩进\t true表示换行
		OutputFormat format = new OutputFormat("\t",true);
		XMLWriter writer = new XMLWriter(new FileOutputStream(path),format);
		writer.write(doc);
		writer.flush();
		writer.close();
		
	}
	
	//解析xml字符串
	public static Document parseStr(String xmlStr) throws DocumentException{
		Document doc = DocumentHelper.parseText(xmlStr);
		return doc;
	}
	
	public static void main(String[] args) throws IOException, DocumentException {
//		write("src/main/java/com/test/xml/school.xml");
		
		read("src/main/java/com/test/xml/school.xml");
		
	}

}
