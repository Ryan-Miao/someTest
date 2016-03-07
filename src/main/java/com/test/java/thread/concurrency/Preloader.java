package com.test.java.thread.concurrency;

import net.sf.ehcache.util.ProductInfo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * java并发编程实战
 * 5-12
 * futureTask提前加载
 * Created by mrf on 2016/3/7.
 */
public class Preloader {
    private final FutureTask<ProductInfo> future = new FutureTask<ProductInfo>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws Exception {
            return loadProductInfo();
        }

        /**
         *从数据库加载
         */
        private ProductInfo loadProductInfo() {
            return null;
        }
    });
    private final Thread thread = new Thread(future);
    public void start(){
        thread.start();
    }
    public ProductInfo get() throws InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            e.printStackTrace();
        }
        return null;
    }
}
