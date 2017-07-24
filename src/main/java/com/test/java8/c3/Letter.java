package com.test.java8.c3;

/**
 * Created by ryan on 7/24/17.
 */
public class Letter {
    public static String addHeader(String text){
        return "From Ryan Miao: " + text;
    }

    public static String addFooter(String text) {
        return text + " Kind regards";
    }

    public static String checkSpelling(String text){
        return text.replace("<", "&lt;");
    }
}
