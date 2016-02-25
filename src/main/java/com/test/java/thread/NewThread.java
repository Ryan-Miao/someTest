package com.test.java.thread;

/**
 * 学习线程----实现Runnable接口
 * Created by mrf on 2016/2/25.
 */
public class NewThread  implements Runnable{
    Thread thread;
    NewThread(){
        //创建第二个新线程
        thread = new Thread(this,"Demo Thread");
        System.out.println("我是实现Runnable接口的类，我被创建了。而且我开始创建另一个线程(name,priority,groupname)："+thread);
        thread.setDaemon(true);
        thread.start();
    }

//第二个线程入口
    @Override
    public void run() {
        System.out.println("------------------我是实现Runnable接口的运行入口，我要开始运行了。-----------------------");
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Child Thread:"+i);
                //暂停线程
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("Child interrupted");
            e.printStackTrace();
        }
        System.out.println("---------------我这个线程就要运行结束了.------------------------");

    }
}

class ThreadDemo{
    public static void main(String[] args) {
        System.out.println("===========main线程开始运行。==============");
        System.out.println("当前运行的是main线程："+Thread.currentThread());
        new NewThread();//创建一个新线程
        try {
            for (int i =0; i<5; i++){
                System.out.println("main thread:"+i);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("main thread interruped.");
            e.printStackTrace();
        }
        System.out.println("==============main线程运行结束.================");
    }
}
