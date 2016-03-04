package com.test.java.designPattern.singleton;

import java.util.Vector;

/**
 * 单例模式
 * Created by mrf on 2016/2/27.
 * http://www.cnblogs.com/woshimrf/p/5223315.html
 */
public class Singleton {

    /*持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载*/
    private static Singleton instance = null;

    public int i = 0;

    /*私有构造方法，防止被实例化*/
    private Singleton(){
    }

    /*静态工程方法，创建实例
    问题：jvm创建和赋值是两个操作，不是原子的
    a线程判断instance为null，上锁，new了一个实例，这时jvm可能先new了一个内存放进去，但并没有告诉instance准确的地址，即还没赋值。退出锁。
    b线程进来，instance为null。重新创建。。。。因此初始化多次
    * */
    public static  Singleton getInstance(){
        if(instance == null){
            synchronized(Singleton.class){
                System.out.println("创建初始化=================================================================================");
                instance = new Singleton();
            }
        }
        return instance;
    }

    public void testInstance(){
//        instance.i = 2;
    }


    /**结果：
     * 我测试了多次,有时候会初始化两次
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Singleton instance = Singleton.getInstance();
                    instance.testInstance();
                }
            }).start();
        }
    }

    /*如果该对象用于序列化，可以保证对象在序列化前后保持一致*/
    public Object readResolve(){
        return instance;
    }

}

/**
 * 改进，因为jvm创建instance这个实例也会出问题，创建变量和赋值是两个操作，顺序不定。
 */
class Singleton2{

    /*私有构造方法，防止被实例化*/
    private Singleton2(){
    }

    /*此处使用一个内部类来维护单例*/
    private static class SingletonFactory{
        private static Singleton2 instance = new Singleton2();
    }
    /*获取实例*/
    public static Singleton2 getInstance(){
        return SingletonFactory.instance;
    }


    /*如果该对象用于序列化，可以保证对象在序列化前后保持一致*/
    public Object readResolve(){
        return getInstance();
    }

}

/**
 * 安全的实现了单例
 */
class Singleton3{
    private static  Singleton3 instance = null;
    public int i;

    private Singleton3(){
    }

    private static synchronized void syncInit(){
        if(instance == null){
            System.out.println("Singleton3创建初始化======================================================");
            instance = new Singleton3();
        }
    }

    public static Singleton3 getInstance(){
        if(instance ==null){
            syncInit();
        }
        return instance;
    }
    public void testInstance(){
    }
    /**结果：
     * 我测试了多次没发现问题
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Singleton3 instance = Singleton3.getInstance();
                    instance.testInstance();
                }
            }).start();
        }
    }
}

/**
 * 影子实例：为单例对象的属性同步更新
 */
class SingletonTest{
    private static SingletonTest instance = null;
    private Vector properties = null;

    public Vector getProperties(){
        return properties;
    }

    private SingletonTest(){
    }

    private static synchronized void synInit(){
        if (instance ==null){
            instance = new SingletonTest();
        }
    }

    public static SingletonTest getInstance(){
        if(instance == null){
            synInit();
        }
        return instance;
    }

    public void updateProperties(){
        SingletonTest shadow = new SingletonTest();
        properties = shadow.getProperties();
    }
}
