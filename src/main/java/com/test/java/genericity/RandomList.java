package com.test.java.genericity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2015/12/25.
 * 编程思想 第15章 泛型 p625
 * 一个持有特定类型对象的列表
 */
public class RandomList <T>{
    private ArrayList<T> storage = new ArrayList<>();
    private Random rand = new Random(47);
    public void add(T item){
        storage.add(item);
    }
    public T select(){
        return storage.get(rand.nextInt(storage.size()));
    }
    public static void main(String[] args){
        RandomList<String> rs = new RandomList<>();
        for (String s : "The quick brown fox jumped over the lazy brown dog".split(" ")) {
            rs.add(s);
        }
        for (int i = 0; i < 11; i++) {
            System.out.print(rs.select()+ " ");
        }

    }
}
