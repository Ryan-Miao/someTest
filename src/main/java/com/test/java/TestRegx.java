package com.test.java;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rmiao on 6/21/2016.
 */
public class TestRegx {

    public static void main( String args[] ){

        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
        } else {
            System.out.println("NO MATCH");
        }
    }


    @Test
    public void testRegDate() throws ParseException {
        String date="2016-06-21";
        String pattern="^\\d{4}(-|/|\\.)\\d{1,2}\\1\\d{1,2}$";
        boolean b = isTrue(date, pattern);
        System.out.println(b);

        String alerts = "mongo/alerts/contentAlerts-2017-12-01.json";

        if (isTrue(alerts,"^mongo/alerts/contentAlerts(-)\\d{4}(-)\\d{2}\\1\\d{2}\\.json$")){
            System.out.println(alerts.split("/")[2].substring(14,24));
            System.out.println(new SimpleDateFormat("yy-MM-dd").parse(alerts.split("/")[2].substring(14,24)));
        }


    }

    private boolean isTrue(String line, String pattern) {
        return Pattern.compile(pattern).matcher(line).find();
    }
}
