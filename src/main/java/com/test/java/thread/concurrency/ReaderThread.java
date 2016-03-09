package com.test.java.thread.concurrency;

import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * java并发编程实战
 * 7-11通过改写interrupt方法将非标准的取消操作封装在Thread中
 * Created by mrf on 2016/3/9.
 */
public class ReaderThread extends Thread{
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException{
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    public void interrupt(){
        try {
            socket.close();
        } catch (IOException e) {

        }finally {
            super.interrupt();
        }
    }
    public void run(){
        try {
            byte[] buf = new byte[1024];
            while (true){
                int count = in.read(buf);
                if (count<0){
                    break;
                }
                else if (count>0){
//                    processBuffer(buf,count);
                }
            }
        } catch (IOException e) {
            //允许线程退出
            e.printStackTrace();
        }
    }

}

/**
 * 7-12通过newTaskFor将非标准的取消操作封装在一个任务中
 */
interface CancellableTask<T> extends Callable<T>{
    void cancel();
    RunnableFuture<T> newTask();
}
@ThreadSafe
class CancellingExecutor extends ThreadPoolExecutor{

    public CancellingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    protected<T>  RunnableFuture<T> newTaskFor(Callable<T> callable){
        if (callable instanceof CancellableTask)
            return ((CancellableTask<T>)callable).newTask();
        else
            return super.newTaskFor(callable);
    }
}
abstract class SocketUsingTask<T> implements CancellableTask<T>{
    @GuardedBy("this") private Socket socket;
    protected synchronized void setSocket(Socket s){socket = s;}
    public synchronized void cancel(){
        try {
            if (socket !=null)
                socket.close();
        } catch (IOException e) {
        }
    }
    public RunnableFuture<T> newTask(){
        return new FutureTask<T>(this){
            public boolean cancel(boolean mayInterruptIfRunning){
                try {
                    SocketUsingTask.this.cancel();
                }finally {
                    return super.cancel(mayInterruptIfRunning);
                }
            }
        };
    }
}
