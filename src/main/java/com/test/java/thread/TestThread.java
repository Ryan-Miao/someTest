package com.test.java.thread;

/**
 * Created by mrf on 2016/2/26.
 */
public class TestThread  implements Runnable{
    int i ;
    String name;

    public TestThread() {
    }

    public TestThread(String name) {
        this.name = name;
    }

    public synchronized void setName(String name){
        this.name = name;
    }

    @Override
    public void run() {
        int k=0;
        for (int j = 0; j < 100; j++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            k++;
            System.out.println(Thread.currentThread()+":"+i+":"+k);
        }

    }

    public static void main(String[] args) throws InterruptedException {
        TestThread a = new TestThread("a");
        Thread t1 = new Thread(a,"thread_a");
        t1.start();

        Thread t2 = new Thread(a,"thread_b");
        t2.start();

    }
}
