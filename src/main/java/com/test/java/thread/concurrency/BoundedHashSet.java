package com.test.java.thread.concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * java 并发编程实战
 * 5-14使用Semaphore做容器设置边界
 * 信号量
 * Created by mrf on 2016/3/8.
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

//    public BoundedHashSet(Set<T> set, Semaphore sem) {
//        this.set = set;
//        this.sem = sem;
//    }

    public BoundedHashSet(int bound){
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        }finally {
            if (!wasAdded){
                sem.release();
            }
        }
    }
    public boolean remove(Object o){
        boolean wasRemoved = set.remove(o);
        if (wasRemoved){
            sem.release();
        }
        return wasRemoved;
    }
}
