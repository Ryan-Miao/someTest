package com.test.java.thread;

/**
 * 多线程学习---继承Thread
 * Created by mrf on 2016/2/25.
 */
public class NewThreadExtend  extends Thread{
    NewThreadExtend(){
        //创建第二个线程
        super("Demo Thread.");
        System.out.println("子线程："+this);
    }

    //通过重写run方法来实现线程业务逻辑
    public void run(){
        System.out.println("-------我是子线程，我开始运行----------------");
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("子线程："+i);
                Thread.sleep(50);
            }
        } catch (InterruptedException e) {
            System.out.println("子线程interrupted");
            e.printStackTrace();
        }
        System.out.println("-------我是子线程，我结束运行----------------");
    }

}

class ExtendThread{
    public static void main(String[] args) {
        System.out.println("========我是main线程，我开始运行："+Thread.currentThread());
        NewThreadExtend newThreadExtend = new NewThreadExtend();
        newThreadExtend.start();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("main线程："+i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("main线程interrupted.");
            e.printStackTrace();
        }
        System.out.println("========main线程运行结束=====================");
    }
}
