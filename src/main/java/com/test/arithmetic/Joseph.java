package com.test.arithmetic;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 * 约瑟夫环
 * n个人一圈，1-m循环报数，剩下的编号？
 */
public class Joseph {

    /**
     * 遍历--数组
     * 时间复杂度O(mn)
     */
    public static void forall(int n, int m) {
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = 0;//初始化
        }
        System.out.println();
        int t = 0;//指针\
        int s = 0;//报数
        int f = 0;//死亡人数
        int count = 0;//计算次数
        while (f != n) {
            count++;
            if (t == n) {//t指向第一个
                t = 0;
            }
            if (a[t] == 0) {//t位置的人存在
                s++;//报数
            }
            if (s == m) {
                s = 0;//清零
                a[t] = 1;//标记死亡
                ++f;//死亡人数++
                if(f==n){
                    System.out.print(t + 1 + " ");//输出
                    break;
                }
            }
            ++t;
        }
        System.out.println();
        System.out.println("计算次数：" + count);
    }

    /**
     * 遍历--ArrayList
     * 这里忽略了list的内部实现，似乎并不好表示复杂度
     */
    public static int forallArrayList(int n,int m) throws InterruptedException {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i+1);
        }
        int len = 0;//移除的个数
        int t=0;//指针
        int s = 0;//报数
        int count=0;//计算次数
        while(len!=n){
            count++;
            s++;//报数
            if(t==(n-len)){
                t=0;
            }
            if(s==m){//若为m
                s=0;//清零
                len++;//移除的个数
                if(len==n){
                    System.out.print(list.get(t)+" ");
                    break;
                }
                list.remove(t);
                //list会调节下一个到本位置，不用指针移动
            }else{
                t++;//指针移到下一个
            }

        }
        System.out.println("\n计算次数："+count);

        return 0;
    }

    /**
     * 数列推导
     * /*
     * If number = 3
     * f(1) = 0
     * f(2) = 1 = (f(1) + 3) % 2
     * f(3) = 1 = (f(2) + 3) % 3
     * f(4) = 0 = (f(3) + 3) % 4
     * f(5) = 3 = (f(4) + 3) % 5
     * ...
     * f(n) = x = (f(n-1) + 3) % n
     *
     */
    public static void sequenceInfer(int n, int m){
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last+m)%i;
        }
        System.out.println(last+1+" ");
    }

    /**
     * 循环链表
     */
    public static void circleList(int n, int m){
        String h = "head";
        CircleList circleList = new CircleList(h);
        for (int i = 1; i < n+1; i++) {
            circleList.addLast(i);
        }
//        System.out.println(circleList);
        LinkedListNode head = circleList.getHead();
        LinkedListNode node = head.next;
        int s = 1;
        while (circleList.size>0){
            while (s!=m){
                node = node.next;
                if(node.object==h){
                    node=node.next;
                }
                s++;
            }
            s=0;
            if(circleList.size==1){
                System.out.println(node.object+" ");
                break;
            }
            node = circleList.remove(node);
        }
    }



    public static void main(String[] args) throws InterruptedException {
        int n =4444441;
        int m = 3;
        long a = System.currentTimeMillis();
        forall(n, m);
        long b = System.currentTimeMillis();
        System.out.println("array用时：" + (b - a));

//        long s = System.currentTimeMillis();
//        forallArrayList(n,m);
//        long e = System.currentTimeMillis();
//        System.out.println("list用时："+(e-s));

        /**维护ArrayList的开销很大啊
         38996
         计算次数：43026596
         array用时：94
         38996
         计算次数：3582300
         list用时：108622
         */
        long s1 = System.currentTimeMillis();
        sequenceInfer(n,m);
        long e1 = System.currentTimeMillis();
        System.out.println("数列推导公式用时："+(e1-s1));
        long s2 = System.currentTimeMillis();
        circleList(n,m);
        long e2 = System.currentTimeMillis();
        System.out.println("循环链表用时："+(e2-s2));

    }
}

/**
 * 链表节点
 */
class LinkedListNode{
    public LinkedListNode previous;//前一节点
    public LinkedListNode next;//后一节点
    public Object object;//节点的值
    public long timestamp;//修改时间

    public LinkedListNode(LinkedListNode previous, LinkedListNode next, Object object) {
        this.previous = previous;
        this.next = next;
        this.object = object;
        timestamp = System.currentTimeMillis();
    }
    public void remove(){
        previous.next = next;
        next.previous = previous;
    }

    @Override
    public String toString() {
        String s = previous == null ? "null" : previous.object.toString();
        String s1 = next == null ? "null" : next.object.toString();

        return "LinkedListNode{" +
                "previous=" + s +
                ", next=" +  s1+
                ", object=" + object +
                ", timestamp=" + timestamp +
                '}';
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(null,null,"head");
        LinkedListNode node = new LinkedListNode(null,null,"node");
        node.next = head.next;
        node.previous = head;
        node.previous.next = node;
        System.out.println(node);
        System.out.println(head);

    }
}

/**
 * 循环链表
 */
class CircleList{
    int size = 0;
    //头指针，第一个节点的前面，最后一个节点的后面
    private LinkedListNode head = new LinkedListNode(null,null,"head");

    public LinkedListNode getHead() {
        return head;
    }

    public CircleList() {
        head.next = head.previous = head;
    }
    public CircleList(Object object) {
        head.object = object;
        head.next = head.previous = head;
    }

    public LinkedListNode getFirst(){
        LinkedListNode node = head.next;
        if(node == head){
            return null;
        }
        return node;
    }

    public LinkedListNode getLast(){
        LinkedListNode node = head.previous;
        if(node == null){
            return null;
        }
        return node;
    }

    /**
     * 插入第一个位置，head结点之后
     * @param node
     * @return
     */
    public LinkedListNode addFirst(LinkedListNode node){
        node.next = head.next;
        node.previous = head;
        node.previous.next = node;
        node.next.previous = node;
        size++;
        return node;
    }

    /**
     * 将此值插入到链表的第一个位置,head之后
     * @param object
     * @return
     */
    public LinkedListNode addFirst(Object object){
        LinkedListNode node = new LinkedListNode(head, head.next, object);
        node.previous.next = node;
        node.next.previous = node;
        size++;
        return node;
    }

    /**
     * 将此值插入到链表的最后一个位置，head之前
     * @param object
     * @return
     */
    public LinkedListNode addLast(Object object){
        LinkedListNode node = new LinkedListNode(head.previous, head, object);
        node.previous.next = node;
        node.next.previous = node;
        size++;
        return node;
    }

    /**
     * 移除节点
     * @param node
     */
    public LinkedListNode remove(LinkedListNode node){
        LinkedListNode next = null;
        if(node!=null){
            next = node.previous;
            node.remove();
            size--;
        }
        return next;
    }

    //清空
    public void clear(){
        LinkedListNode node = getLast();
        while(node!=null){
            node.remove();;
            node=getLast();
        }
        //re -initialize
        head.next = head.previous = head;
        size=0;
    }

    public String toString(){
        LinkedListNode node = head.next;
        StringBuffer sb = new StringBuffer();
        sb.append(head.object+"->");
        while (node!=head){
            sb.append(node.object).append("->");
            node = node.next;
        }
        sb.append("head");

        return sb.toString();
    }

    public static void main(String[] args) {
        CircleList circleList = new CircleList();
        for (int i = 0; i < 10; i++) {
            circleList.addLast(i);
        }
        System.out.println(circleList.getHead());
        System.out.println(circleList.toString());
    }
}
