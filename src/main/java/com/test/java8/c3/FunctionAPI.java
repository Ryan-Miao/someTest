package com.test.java8.c3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Ryan on 2017/07/19/0019.
 */
public class FunctionAPI {

    private <T>  List<T> filter(List<T> list, Predicate<T> p){
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)){
                results.add(t);
            }
        }
        return results;
    }
    @Test
    public void testPredicate(){
        List<String> list = Arrays.asList("aa","bbb","ccc");
        List<String> noEmpty = filter(list, (String s) -> !s.isEmpty());
    }


    private <T>  void
}
