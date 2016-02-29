package com.test.java.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 学习lock锁
 * Created by mrf on 2016/2/29.
 */
public class TestLock {
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Lock lock = new ReentrantLock();//注意这个地方
    public static void main(String[] args){
        final TestLock testLock = new TestLock();

        new Thread(){
            public void run(){
                testLock.insert(Thread.currentThread());
            }
        }.start();
        for (int i = 0; i < 5; i++) {
            new Thread(){
                public void run(){
                    testLock.insert(Thread.currentThread());
                }
            }.start();
        }

    }

    private void insert(Thread thread) {

        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for (int i = 0; i <5 ; i++) {
                arrayList.add(i);
            }
        }catch (Exception e){
            //ToDo:handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}

/**
 * 可以立即返回是否获得锁的结果
 */
class TestTryLock{
    private ArrayList<Integer> arrayList = new ArrayList<>();
    private Lock lock = new ReentrantLock();//注意这个地方
    public static void main(String[] args){
        final TestTryLock testLock = new TestTryLock();
        for (int i = 0; i < 5; i++) {
            new Thread(){
                public void run(){
                    testLock.insert(Thread.currentThread());
                }
            }.start();
        }

    }

    private void insert(Thread thread) {
        if (lock.tryLock()){
            try {
                System.out.println(thread.getName()+" ：得到了锁");
                for (int i = 0; i <5 ; i++) {
                    arrayList.add(i);
                }
//                Thread.sleep(10);
            }catch (Exception e){
                //ToDo:handle exception
            }finally {
                System.out.println(thread.getName()+" ：释放了锁");
                lock.unlock();
            }
        }else{
            System.out.println(thread.getName()+"获取锁失败");
        }
    }
}

/**
 * 可以被中断
 */
class TestLockInterruptibly{
    private Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        TestLockInterruptibly testLockInterruptibly = new TestLockInterruptibly();
        MyThread thread1 = new MyThread(testLockInterruptibly);
        MyThread thread2 = new MyThread(testLockInterruptibly);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();

    }
    private void insert(Thread thread) throws InterruptedException {
            lock.lockInterruptibly();//注意：如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
            try {
                System.out.println(thread.getName()+" ：得到了锁");
                long start = System.currentTimeMillis();
                for (;;){
                    if (System.currentTimeMillis()-start >=Integer.MAX_VALUE)
                        break;
                    //插入数据
                }
            }finally {
                System.out.println(Thread.currentThread().getName()+"执行finally");
                lock.unlock();
                System.out.println(thread.getName()+"释放了锁");
            }

    }

   static  class MyThread extends Thread{
        private TestLockInterruptibly testLockInterruptibly = null;
        public MyThread(TestLockInterruptibly testLockInterruptibly){
            this.testLockInterruptibly = testLockInterruptibly;
        }

        @Override
        public void run() {
            try{
                testLockInterruptibly.insert(Thread.currentThread());
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()+"被中断");
//                e.printStackTrace();
            }
        }
    }
}

/**
 * 读写锁
 */
class TestReadWriteLock{
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final TestReadWriteLock test = new TestReadWriteLock();
        new Thread(){
            public void run(){
                test.get2(Thread.currentThread());
            }
        }.start();
        new Thread(){
            public void run(){
                test.get2(Thread.currentThread());
            }
        }.start();
    }

    public synchronized void get(Thread thread){
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis()-start<=1){
            System.out.println(thread.getName()+"正在进行读操作");
        }
        System.out.println(thread.getName()+"读操作完毕");
    }

    public void get2(Thread thread){
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis()-start<=1){
                System.out.println(thread.getName()+"正在进行读操作");
            }
            System.out.println(thread.getName()+"读操作完毕");
        }finally {
            rwl.readLock().unlock();
        }
    }
}





