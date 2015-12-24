package com.test.arithmetic.chapter1;

import java.util.Iterator;

/**
 * Created by Administrator on 2015/12/24.
 * 算法第四版 第一章 下压栈（lifo）
 */
public class ResizingArrayStack<Item> implements Iterator<Item>{

    private Item[] a = (Item[]) new Object[1];//栈元素
    public boolean hasNext() {
        return false;
    }

    @Override
    public Item next() {
        return null;
    }

    @Override
    public void remove() {

    }
}
