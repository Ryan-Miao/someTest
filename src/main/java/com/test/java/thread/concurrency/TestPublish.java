package com.test.java.thread.concurrency;

/**
 * 对象不正确的发布
 * Created by mrf on 2016/3/4.
 */
public class TestPublish {
    //不安全的发布
    private Holder holder;
    public void initialize(){
        holder = new Holder(12);
    }

    /**
     * 测试结果不明显，但应该明白：除了创建holder的线程，其他线程如果并发读取holder的话，
     * holder的状态是不确定的。因为初始化和赋值是两个操作，就想在测试Singleton里那样，应该将创建单独拿出来上锁。
     * 在singleton中的一个方法中，
     * @param args
     */
    public static void main(String[] args) {
        final TestPublish testPublish = new TestPublish();
        testPublish.initialize();

        for (int i = 0; i < 1000; i++) {
            new Thread(){
                public void run(){
                    testPublish.holder.asserSanity();
                }
            }.start();
        }
    }
}

class Holder{
    private int n;
    public Holder(int n){
        this.n= n;
    }
    public void asserSanity(){
        if(n!=n){
            throw new AssertionError("This statement is false.");
        }
    }
}
