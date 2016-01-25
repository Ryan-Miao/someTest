package com.test.java.genericity;

/**
 * Created by Administrator on 2015/12/25.
 * 编程思想 第15章 泛型 p625
 * 不用LinkedList来是先自己的内部链式存储机制
 */
public class LinkedListStack<T> {
    private static class Node<U> {
        U item;
        Node<U> next;
        Node(){
            item =null;
            next = null;
        }
        Node(U item,Node<U> next){
            this.item = item;
            this.next = next;
        }
        boolean end(){
            return item ==null && next == null;
        }
    }

    private Node<T> top = new Node<T>();//end sentinel
    public void push(T item){
        top = new Node<T>(item,top);
    }
    public T pop(){
        T result = top.item;
        if(!top.end()){
            top = top.next;
        }
        return result;
    }
    public static void main(String[] args){
        LinkedListStack<String> lss = new LinkedListStack<>();
        for (String s : "Phasers on stun!".split(" ")) {
            lss.push(s);
        }
        String s ;
        while((s=lss.pop())!=null){
            System.out.println(s);
        }
    }

}
