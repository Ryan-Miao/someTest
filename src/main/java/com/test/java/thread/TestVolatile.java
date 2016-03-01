package com.test.java.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试volatile关键字
 * Created by mrf on 2016/3/1.
 */
public class TestVolatile {
//    public volatile int inc = 0;
    Lock lock = new ReentrantLock();
//volatile关键字不能保证原子
//    public  void increase() {
//        inc++;
//    }
    //可以保证原子
//    public synchronized void increase() {
//        inc++;
//    }

    //可以保证原子
//    public void increase(){
//        lock.lock();
//        try {
//            inc++;
//        }finally {
//            lock.unlock();
//        }
//    }

    //快且原子
    public AtomicInteger inc = new AtomicInteger();

    public void increase(){
        inc.getAndIncrement();
    }




    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final TestVolatile test = new TestVolatile();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    for (int j = 0; j < 10000; j++)
                        test.increase();
                }
            }.start();
        }
            while(Thread.activeCount()>2)  //保证前面的线程都执行完
                Thread.yield();
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(test.inc);
    }
}
