package com.test.web.escape;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by rmiao on 9/14/2016.
 */
public class StringEscape {
    public static void main(String[] args) {
        String html = "<a href=\"javascript:alert(1)\">a label注入成功</a>";
        String result = StringEscapeUtils.escapeHtml4(html);
        System.out.println(result);


        String str = "中国 - <a src='aaa'>a</a>  console.log  <script></script>";
        System.out.println("用escapeJava方法转义之后的字符串为:"+StringEscapeUtils.escapeJava(str));
        System.out.println("用unescapeJava方法反转义之后的字符串为:"+StringEscapeUtils.unescapeJava(StringEscapeUtils.escapeJava(str)));

        System.out.println("用escapeHtml方法转义之后的字符串为:"+StringEscapeUtils.escapeHtml4(str));
        System.out.println("用unescapeHtml方法反转义之后的字符串为:"+StringEscapeUtils.unescapeHtml4(StringEscapeUtils.escapeHtml4(str)));

        System.out.println("用escapeXml方法转义之后的字符串为:"+StringEscapeUtils.escapeXml11(str));
        System.out.println("用unescapeXml方法反转义之后的字符串为:"+StringEscapeUtils.unescapeXml(StringEscapeUtils.escapeXml11(str)));

        System.out.println("用escapeJavaScript方法转义之后的字符串为:"+StringEscapeUtils.escapeEcmaScript(str));
        System.out.println("用unescapeJavaScript方法反转义之后的字符串为:"+StringEscapeUtils.unescapeEcmaScript(StringEscapeUtils.escapeEcmaScript(str)));
    }
}
