package com.test.java.thread.concurrency;

import java.util.EventListener;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 测试this逸出
 * Created by mrf on 2016/3/3.
 */
public class ThisEscape {
    public ThisEscape(Eventsource eventsource) throws InterruptedException {
        eventsource.registerListener(
                new EventListener() {
                    @Override
                    public String toString() {
                        return "我是匿名类";
                    }
                }
        );
        Thread.sleep(3000);
        System.out.println("构造完毕");
    }

    public static void main(String[] args) throws InterruptedException {
        Eventsource eventsource = new Eventsource();
        ThisEscape thisEscape = new ThisEscape(eventsource);
    }
}

class Eventsource {
    public Eventsource() {
    }

    public void registerListener(EventListener object) {
        System.out.println(object);
    }


    public static void main(String[] args) {
        A a = new A();
        Thread t = new Thread(a);
        t.setName("AAAA-IDEA-Test");
        t.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        a.setFlag(false);
        System.out.println("main结束");
    }
}

class A implements Runnable {
      boolean flag = true;
    long i = 0;

    @Override
    public void run() {
        while (flag) {
            i++;
//            System.out.println(i);
        }
        System.out.println("i=" + i++);
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }



}

