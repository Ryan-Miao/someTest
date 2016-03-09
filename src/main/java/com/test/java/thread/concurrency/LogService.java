package com.test.java.thread.concurrency;

import org.apache.http.annotation.GuardedBy;

import java.io.PrintWriter;
import java.util.concurrent.*;

/**
 * java并发编程
 * 7-15中断线程
 * 向LogWriter添加可靠的取消操作
 * Created by mrf on 2016/3/9.
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    @GuardedBy("this") private boolean isShutdown;
    @GuardedBy("this") private  int reservations;

    public LogService(BlockingQueue<String> queue, LoggerThread loggerThread, PrintWriter writer, boolean isShutdown, int reservations) {
        this.queue = queue;
        this.loggerThread = loggerThread;
        this.writer = writer;
        this.isShutdown = isShutdown;
        this.reservations = reservations;
    }

    public void start(){
        loggerThread.start();
    }
    public void stop(){
        synchronized (this){
            isShutdown = true;
        }
        loggerThread.interrupt();
    }
    private class LoggerThread extends Thread{
        public void run(){
            try{
                synchronized(LogService.this){
                    if (isShutdown && reservations==0){
                        //break;
                    }
                }
                String msg = queue.take();
                synchronized (LogService.this){--reservations;}
                writer.print(msg);
            } catch (InterruptedException e) {
                //retry
                e.printStackTrace();
            }finally {
                writer.close();
            }
        }
    }
}

/**
 * 7-16使用ExecutorService的日志服务
 */
class LogService2{
    private final ExecutorService exec = Executors.newSingleThreadExecutor();
    private final BlockingQueue<String> queue;
//    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    @GuardedBy("this") private boolean isShutdown;
    @GuardedBy("this") private  int reservations;

    LogService2(BlockingQueue<String> queue, PrintWriter writer, boolean isShutdown, int reservations) {
        this.queue = queue;
        this.writer = writer;
        this.isShutdown = isShutdown;
        this.reservations = reservations;
    }

    public void start(){}
    public void stop() throws InterruptedException {
        try {
            exec.shutdown();
            exec.awaitTermination(1000,TimeUnit.SECONDS);
        }finally {
            writer.close();
        }
    }
    public void log(String msg){
        try {
//            exec.execute(new WriteTask(msg));
        }catch (RejectedExecutionException ignored){

        }
    }
}
