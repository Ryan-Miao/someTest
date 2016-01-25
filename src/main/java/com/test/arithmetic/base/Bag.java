package com.test.arithmetic.base;

import java.util.Iterator;

/**
 * Created by Administrator on 2015/12/25.
 */
public class Bag<Item> implements Iterable<Item>{
    private Node first;
    private class Node{
        Item item;
        Node next;
    }
    public void add(Item item){
        //和stack的push相同
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{

        private Node current = first;
        @Override
        public boolean hasNext() {
            return current !=null;
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
}
