package com.test.java.thread;

/**
 * synchronized
 * Created by mrf on 2016/2/26.
 */
public class ThreadA extends Thread {
    private Demo demo;
    public ThreadA(Demo demo){
        this.demo = demo;
    }
    public void run(){
        demo.getvalue();
    }

    public static void main(String[] args) throws InterruptedException {
        Demo demo = new Demo();
        for (int i = 0; i < 100; i++) {
            new ThreadA(demo).start();
        }
        Thread.sleep(500);
        System.out.println(demo.getI());
    }
}


class Demo{
    private int i = 0;
    public synchronized int getvalue(){
        return i++;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}