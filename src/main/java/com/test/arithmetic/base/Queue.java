package com.test.arithmetic.base;

/**
 * Created by Administrator on 2015/12/25.
 * 算法第四版 第一章 基础
 * 先进先出队列
 */
public class Queue<Item> {
    private Node first;//指向最早添加的结点的链接
    private Node last;//指向最近添加的结点的链接
    private int N; //队列中的元素数量
    private class Node{
        //定义结点的嵌套类
        Item item;
        Node next;
    }
    public boolean isEmpty(){return first ==null;}
    public int size(){return N;}
    public void enqueue(Item item){
        //向表尾添加元素
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) first = last;
        else oldlast.next = last;
        N++;
    }
    public Item dequeue(){
        //从表头删除元素
        Item item = first.item;
        first = first.next;
        if(isEmpty()){
            last=null;
        }
        N--;
        return item;
    }

    public static void main(String[] args) {
        Queue<String> q = new Queue<>();
        for (String s : "ce shi dui lie".split(" ")) {
            q.enqueue(s);
        }
        System.out.println(q.size());
        while(!q.isEmpty())
        System.out.print(" "+q.dequeue()+" ");

    }

}
