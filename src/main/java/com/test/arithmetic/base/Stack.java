package com.test.arithmetic.base;

import java.util.Iterator;

/**
 * Created by Administrator on 2015/12/25.
 * 算法第四版 第一章  基础
 * 下压堆栈 链表实现
 */
public class Stack<Item> implements Iterable<Item>{
    private Node first;//栈顶（最近添加的元素）
    private int N;//元素数量

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    private class ListIterator implements  Iterator<Item>{

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }
    }

    private class Node{
        Item item;
        Node next;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public int size(){return N;}
    public void push(Item item){
        //向栈顶添加元素
        Node oldfirst = first;
        first  = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }
    public Item pop(){
        //从栈顶删除元素
        if(isEmpty())
            return null;
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for (String s : "ce shi zhan".split(" ")) {
            stack.push(s);
        }
        System.out.println(stack.size());
        String s ;
        //空指针问题
        while ((s=stack.pop())!=null){
            System.out.print(" "+s);
        }
    }

}

