package com.test.java.thread.concurrency;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * java并发编程实战
 * 6-9错误的Timer行为
 * Created by mrf on 2016/3/8.
 */
public class TestTimer {
    public static void main(String[] args) throws InterruptedException, ParseException {
        Timer timer = new Timer();
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(10);
        timer.schedule(new ThrowTask(),1);
        Thread.sleep(10);
    }
    static class ThrowTask extends TimerTask{
        public void run(){
            throw new RuntimeException();
        }
    }
}

