package com.test.arithmetic.base;

import java.util.Iterator;

/**
 * Created by Administrator on 2015/12/24.
 * 算法第四版 第一章 下压栈（lifo）
 * 能够动态调整数组大小的实现
 */
public class ResizingArrayStack<Item> implements Iterable<Item>{

    private Item[] a = (Item[]) new Object[1];//栈元素
    private int N = 0;//元素数量
    private boolean isEmpty(){return N==0;}
    public int size(){return N;}
    private void resize(int max){
        //将栈移动到一个大小为max的新数组
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a =temp;
    }
    public void push(Item item){
        //将元素添加到栈顶
        if(N==a.length) resize(2*a.length);
        a[N++] = item;
    }
    public Item pop(){
        Item item = a[--N];//取出第n个元素，并将元素数量n置为n-1
        a[N] = null;//取出后取消对象引用，避免对象游离。需要强调的是：我开始以为n会越界，因为最后一个是n-1,而事实上，在前一句代码--n已经-1了。
        if(N>0 && N==a.length/4) resize(a.length/2);
        return item;//疑问：当n=0的时候怎么办
    }
    public Iterator<Item> iterator(){
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = N;
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return a[--i];
        }

        @Override
        public void remove() {

        }
    }
}
