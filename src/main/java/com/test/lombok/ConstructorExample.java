package com.test.lombok;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Date;

/**
 * Created by Ryan Miao on 1/19/18.
 */
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ConstructorExample<T> {
    private int x, y;
    @NonNull
    private T description;

    @NoArgsConstructor
    public static class NoArgsExample {
        @NonNull private String field;
    }

    @RequiredArgsConstructor
    public static class RequiredArgsExample {
        @NonNull private String field;
        private Date date;
        private Integer integer;
        private int i;
        private boolean b;
        private Boolean aBoolean;
    }


    public static void main(String[] args) {
        ConstructorExample example = new ConstructorExample(1,2,"desc");
        NoArgsExample noArgsExample = new NoArgsExample();
        ConstructorExample.of("of initialized.");
    }
}
