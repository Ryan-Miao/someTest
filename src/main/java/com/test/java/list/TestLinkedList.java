package com.test.java.list;

import java.util.*;

/**
 * Created by Administrator on 2015/12/4.
 * 参考：http://blog.csdn.net/i_lovefish/article/details/8042883
 */
public class TestLinkedList {

    public static void main(String[] args) {
        TestLinkedList t = new TestLinkedList();
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < 12; i++) {
            list.add(""+i);
        }
        System.out.println(list.getFirst());
        System.out.println(list.getLast());

        System.out.println("=============遍历====================");
        t.get(list);

        System.out.println("=============抽取子表==================");
        t.getChild(list);
        System.out.println("=============插入=====================");
        t.insert(list);
        System.out.println("=============删除=====================");
        t.delete(list);
        System.out.println("=============清空=======================");
        t.clearlist(list);
        System.out.println("=============栈=======================");
        t.linkStack(list);
        System.out.println("=============队列=======================");
        t.linkQueue(list);
        System.out.println("=============转换为arrayList=======================");
        t.convertArrayList(list);
        System.out.println("=============转换为array=======================");
        t.convertArray(list);
        System.out.println("=============转换为同步的=======================");
        t.syn(list);
        System.out.println("=============查找=======================");
        t.find(list);
    }

    /**
     * 遍历
     * @param list
     */
    public void get(List<String> list){
        for (String s : list) {
            System.out.print(s+" ");
        }
        System.out.println();
    }

    /**
     * 从链表生成子表
     */
    public void getChild(LinkedList<String> list){
        //从2开始，包括2，不包含4
        List<String> clist = list.subList(2, 4);
        System.out.println(clist);

    }

    /**
     * 插入
     * @param list
     */
    public void insert(LinkedList<String> list){
        list.addFirst("first");
        list.addLast("last");
        list.add(3,"medium");
        System.out.println(list);
    }

    /**
     * 删除
     * @param list
     */
    public void delete(LinkedList<String> list){
        System.out.println("remove()");
        String remove = list.remove();
        System.out.println("删除元素可以直接获取："+remove);
        System.out.println(list);
        System.out.println("removeFirst()");
        list.removeFirst();
        System.out.println(list);
        System.out.println("removeLast()");
        list.removeLast();
        System.out.println(list);
        System.out.println("remove(3)");
        list.remove(3);
        System.out.println(list);
        System.out.println("清空2-5");
        list.subList(2,5).clear();
        System.out.println(list);

    }

    /**
     * 删除所有元素，清空
     */
    public void clearlist(List list){
        List listcopy = new LinkedList(list);
        System.out.println("原件："+list);
        System.out.println("拷贝："+listcopy);
        listcopy.clear();
        System.out.println("清空拷贝："+listcopy);
        System.out.println("原件："+list);
    }

    /**
     * 使用链表实现栈
     */
    public void linkStack(LinkedList<String> list){
        //进栈
        System.out.println("push");
        list.addFirst("push");
        System.out.println(list);
        //出栈
        System.out.println("pop");
        list.removeFirst();
        System.out.println(list);

    }

    /**
     * 使用链表实现队列
     */
    public void linkQueue(LinkedList<String> list){
        //入队
        System.out.println("put");
        list.addFirst("put");
        System.out.println(list);
        //出队
        System.out.println("pop");
        list.removeLast();
        System.out.println(list);
        //检验队列是否为空
        System.out.println("is empty");
        System.out.println(list.isEmpty());
    }

    /**
     * 转换为arrayList
     */
    public void convertArrayList(LinkedList<String> list){
        ArrayList<String> strings = new ArrayList<>(list);
        System.out.println(strings);
    }

    /**
     * 转换成数组
     */
    public void convertArray(LinkedList<String> list){
        System.out.println("直接转换成数组");
        Object[] objects = list.toArray();
        for (Object object : objects) {
            System.out.print(object+" ");
        }
        System.out.println();
        System.out.println("转换一个新的空数组，数组长度大于list长度,null补全");
        String[] strings = list.toArray(new String[20]);
        for (String string : strings) {
            System.out.print (string+" ");
        }
        System.out.println();

        System.out.println("转换成一个新的空数组，数组长度小于list长度,自动扩增");
        String[] strings1 = list.toArray(new String[1]);
        for (String s : strings1) {
            System.out.print(s+" ");
        }

        System.out.println();
        System.out.println("转换成一个有值的数组，原数组清空");
        String [] a = {"one","two"};
        String[] strings2 = list.toArray(a);
        for (String s : strings2) {
            System.out.printf(s+" ");
        }
        System.out.println();

    }

    /**
     * 同步方法（干啥的？）
     * 将不安全的实现转换为安全的
     */
    public void syn(LinkedList list){
        Collection collection = Collections.synchronizedCollection(list);
    }

    /**
     * 查询
     * @param list
     */
    public void find(LinkedList list){
        list.add("8");
        list.add("8");
        System.out.println(list);
        System.out.println("查询第一所在位置");
        System.out.println(list.indexOf("8"));
        System.out.println("查询最后一个所在位置");
        System.out.println(list.lastIndexOf("8"));
        System.out.println("查询是否存在");
        System.out.println(list.contains("4"));

    }

    /**
     * 替换
     * @param list
     */
    public void relace(LinkedList list){
        list.set(2,"replace");
        System.out.println(list);
    }



}
