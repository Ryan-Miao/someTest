package com.test.java.thread.concurrency;

import java.io.File;
import java.io.FileFilter;
import java.io.InterruptedIOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * java并发编程实战
 * 5.3.1桌面搜索
 * 爬虫查找所有文件并放入队列
 * Created by mrf on 2016/3/7.
 */
public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            //恢复中断
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    private void crawl(File root) throws  InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries!=null){
            for (File entry : entries) {
                if (entry.isDirectory()){
                    crawl(entry);
                }else if (!alreadyIndexed(entry)){
                    fileQueue.put(entry);
                }
            }
        }
    }

    private boolean alreadyIndexed(File entry){
        //检查是否加入索引
        return false;
    }
}

/**
 * 消费者
 * 将爬虫结果队列取出并加入索引
 */
class Indexer implements Runnable{
    private static final int BOUND = 100;
    private static final int N_CONSUMERS = 2;
    private final BlockingQueue<File> queue;

    Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true){
                indexFile(queue.take());
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File take) {
        //将文件加入索引
    }

    public static void startIndexing(File[] roots){
        BlockingQueue<File> queue = new LinkedBlockingDeque<>(BOUND);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        for (File root:roots) {
            new Thread(new FileCrawler(queue,fileFilter,root)).start();
        }
        for (int i = 0; i < N_CONSUMERS; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}
