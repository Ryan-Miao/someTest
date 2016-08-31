package com.test.cache.guava;

import com.google.common.base.Stopwatch;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * learning and test guava cache
 * Created by Ryan on 2016/8/31 0031.
 */
public class Example {

    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);

    private LoadingCache<String, ObjFromRemote> graphs = CacheBuilder.newBuilder()
            .recordStats()
            .maximumSize(1)
            .build(new CacheLoader<String, ObjFromRemote>() {
                @Override
                public ObjFromRemote load(String key) throws Exception {
                    return getObj(key);
                }
            });

    @Test
    public void testSample() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        //get something from remote server which may spend a long time
        ObjFromRemote obj = getObj("id123");
        System.out.println(obj);
        stopwatch.stop();
        LOGGER.info("The time if not cache cost: {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));

        //=======cache ========

        try {
            stopwatch.reset().start();
            ObjFromRemote id123 = graphs.get("id123");
            stopwatch.stop();
            LOGGER.info("id123 use cache and get for the first time: {} ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
            stopwatch.reset().start();
            ObjFromRemote id123_2 = graphs.get("id123");
            stopwatch.stop();
            LOGGER.info("id123 use cache and get for the second time: {} ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));

            stopwatch.reset().start();
            ObjFromRemote id456 = graphs.get("id456");
            stopwatch.stop();
            LOGGER.info("Get id456 for new: {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
            stopwatch.reset().start();
            ObjFromRemote id456_2 = graphs.get("id456");
            stopwatch.stop();
            LOGGER.info("Get id456 for the second: {} ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));

            stopwatch.reset().start();
            ObjFromRemote id123_3 = graphs.get("id123");
            stopwatch.stop();
            LOGGER.info("id123 use cache and get for the third time: {} ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));stopwatch.reset().start();
            ObjFromRemote id123_4 = graphs.get("id123");
            stopwatch.stop();
            LOGGER.info("id123 use cache and get for the fourth time: {} ms.", stopwatch.elapsed(TimeUnit.MILLISECONDS));

            CacheStats stats = graphs.stats();
            LOGGER.info("The stat \nhitCount:{}  \nhitRate: {} \nevictionCount: {}", stats.hitCount(), stats.hitRate(), stats.evictionCount());

        } catch (ExecutionException e) {
            LOGGER.error("Get value from cache error. ", e);
        }

    }

    private static ObjFromRemote getObj(String key) {
        try {Thread.sleep(1000);} catch (InterruptedException e) {LOGGER.error("sleep error.", e);}
        ObjFromRemote obj = new ObjFromRemote(key, "subject", "body", new Date());

        return obj;
    }
}
