package com.test.java.thread.concurrency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mrf on 2016/3/4.
 */
public class TestThreadLocal {
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){
        public Connection initialValue(){
            try {
                return DriverManager.getConnection("url","username","password");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
    };

    public static Connection getConnection(){
        return connectionHolder.get();
    }
}
//session管理
//    private static final ThreadLocal threadSession = new ThreadLocal();
//
//    public static Session getSession() throws InfrastructureException {
//        Session s = (Session) threadSession.get();
//        try {
//            if (s == null) {
//                s = getSessionFactory().openSession();
//                threadSession.set(s);
//            }
//        } catch (HibernateException ex) {
//            throw new InfrastructureException(ex);
//        }
//        return s;
//    }


/**
 * 测试ThreadLocal变量在不同线程中变量的值是不同的。
 * 因为每个线程都有个副本，然后可以针对副本进行修改而不影响其他线程中变量的副本值
 * ThreadLocal的键值对：键为变量，值为设置的T
 */
class TestThreadLocal2{
    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal<>();
    public void set(){
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong(){
        return longLocal.get();
    }

    public String getString(){
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal2 test = new TestThreadLocal2();
//        test.set();
        System.out.println("===========main线程==============");
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread t1 = new Thread(){
            public void run(){
                test.set();
                System.out.println("===========t线程==============");
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        };
        t1.start();
        t1.join();
        System.out.println("===========main线程==============");
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}

/**
 * 不用set，则必须重写initialValue
 * 设置的内容是可以自己定义的，这里只是示例
 */
class TestThreadLocal3{
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>(){
        protected Long initialValue(){
            return Thread.currentThread().getId();
        }
    };
    ThreadLocal<String> stringLocal = new ThreadLocal<String>(){
        protected String initialValue(){
            return Thread.currentThread().getName();
        }
    };

    public long getLong(){
        return longLocal.get();
    }

    public String getString(){
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        final TestThreadLocal3 test = new TestThreadLocal3();
        System.out.println("===========main线程==============");
        System.out.println(test.getLong());
        System.out.println(test.getString());

        Thread t1 = new Thread(){
            public void run(){
                System.out.println("===========t线程==============");
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        };
        t1.start();
        t1.join();
        System.out.println("===========main线程==============");
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
