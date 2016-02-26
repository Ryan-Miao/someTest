package com.test.java.thread;

/**
 * 死锁
 * Created by mrf on 2016/2/26.
 */
public class DeadLockRisk {
    private static class Resource {
        public int value;
    }

    private Resource resourceA = new Resource();
    private Resource resourceB = new Resource();

    public int read() {
        synchronized (resourceA) {
            synchronized (resourceB) {
                System.out.println("read");
                return resourceB.value + resourceA.value;
            }
        }
    }

    public void write(int a, int b) {
        synchronized (resourceB) {
            synchronized (resourceA) {
                System.out.println("write");
                resourceA.value = a;
                resourceB.value = b;
            }
        }
    }

}

class DeadLockRun implements Runnable{
    DeadLockRisk deadLockRisk = new DeadLockRisk();
    @Override
    public void run() {
        deadLockRisk.write(1,2);
        deadLockRisk.read();
    }

    public static void main(String[] args) {
        DeadLockRun deadLockRun = new DeadLockRun();
        Thread t1 = new Thread(deadLockRun);
        Thread t2 = new Thread(deadLockRun);
        t1.start();
        t2.start();
    }
}
