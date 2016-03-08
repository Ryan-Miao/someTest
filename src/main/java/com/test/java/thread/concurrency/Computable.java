package com.test.java.thread.concurrency;

import org.apache.http.annotation.GuardedBy;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * java并发编程实战
 * 5-16使用HashMap和不同机制来初始化缓存
 * 实现将曾经计算过的命令缓存起来，方便相同的计算直接出结果而不用重复计算
 * Created by mrf on 2016/3/8.
 */
public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction implements Computable<String,BigInteger>{

    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        //在经过长时间的计算后
        return new BigInteger(arg);
    }
}

/**
 * 保守上锁办法
 * 每次只有一个线程能执行compute，性能差
 * @param <A>
 * @param <V>
 */
class Memoizer1<A,V> implements Computable<A,V>{
    @GuardedBy("this")
    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized  V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result==null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}

/**
 * 5-17
 * 改用ConcurrentHashMap增强并发性
 * 但还有个问题，就是只有计算完的结果才能缓存，正在计算的没有缓存，
 * 这将导致一个长时间的计算没有放入缓存，另一个又开始重复计算。
 * @param <A>
 * @param <V>
 */
class Memoizer2<A,V> implements Computable<A,V>{

    private final  Map<A,V> cache = new ConcurrentHashMap<>();
    private final  Computable<A,V> c;

    Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result ==null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}

/**
 * 几乎完美：非常好的并发性，缓存正在计算中的结果
 * 但compute模块中if代码块是非原子性的，这样可能导致两个相同的计算
 * @param <A>
 * @param <V>
 */
class Memoizer3<A,V> implements Computable<A,V>{
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f==null){
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg,ft);
            ft.run();
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            //抛出正在计算
            e.printStackTrace();
        }
        return null;
    }
}

/**
 * 使用ConcurrentHashMap的putIfAbsent解决原子问题
 * 若计算取消则移除
 * @param <A>
 * @param <V>
 */
class Memoizer<A,V> implements Computable<A,V>{
    private final ConcurrentHashMap<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    Memoizer(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if (f==null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return c.compute(arg);
                    }
                };
                FutureTask<V> ft = new FutureTask<V>(eval);
                f = cache.putIfAbsent(arg,ft);
                if (f==null){
                    f = ft;ft.run();
                }
            }
            try {
                return f.get();
            } catch (CancellationException e){
                cache.remove(arg,f);
            } catch(ExecutionException e) {
                //抛出正在计算
                e.printStackTrace();
            }
            return null;
        }

    }
}


