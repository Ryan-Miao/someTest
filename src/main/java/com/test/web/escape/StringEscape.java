package com.test.web.escape;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Created by rmiao on 9/14/2016.
 */
public class StringEscape {
    public static void main(String[] args) {
        String html = "<a href=\"javascript:alert(1)\">a label</a>";
        String result = StringEscapeUtils.escapeHtml4(html);
        System.out.println(result);
    }
}
