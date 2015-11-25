package com.test.regex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public class TestRegex {
    public static void main(String[] args) {
        String patt = "^java\\.lang.*";
        String testStr="java.lang.Long";
        List<String> list = new ArrayList<>();
        list.add("123abc");
        Field[] fields = list.getClass().getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName()+":"+field.getType().getName());
        }
        System.out.println(testStr.matches(patt));

    }
}
