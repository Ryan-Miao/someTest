package com.test.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/**
* @ClassName: TestJsoup 
* @Description: 关于jsoup的一些测试
* @author mrf
* @date 2015-11-7 上午11:25:11 
*
 */
public class TestJsoup {

	public void AnalysisHTMLByString() {
		String html = "<p><a href=\"a.html\">a</p><p> 文本</p>";
		Document doc = Jsoup.parse(html);
		Elements ele = doc.getElementsByTag("p");
		for (Element e : ele) {
			System.out.println(e.text());

		}

	}


	public void AnlysisHTMLByFile() throws IOException {
		File file = new File(System.getProperty("user.dir") + "\\文件");
		Document doc = Jsoup.parse(file, "UTF-8");
		Elements eles = doc.getElementsByTag("a");
		for (Element e : eles) {
			System.out.println(e.text());
			System.out.println(e.attr("href"));
		}
		Element ele = doc.getElementById("btn");
		System.out.println(ele.html());

	}

}
