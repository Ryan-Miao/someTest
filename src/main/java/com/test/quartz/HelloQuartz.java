package com.test.quartz;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 学习使用quartz
 * Created by mrf on 2016/3/3.
 */
public class HelloQuartz  implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Hellow Quarz! - executing its job at "+new Date()+" by "+jobExecutionContext.getTrigger().getKey());
    }
}

class QuartzManager{
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    private static String JOB_GROUP_NAME = "group1";
    private static String TRIGGER_GROUP_NAME = "triiger1";

    public static void addJob(String jobName,Job job,String time) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
//        JobDetail jobDetail = new Job(job.getClass())
    }
}


class TestJob implements Job{
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date date = new Date();
    String returnstr = format.format(date);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(returnstr+"-------------------------------------");
    }
}

class HelloQuartzScheduling{
    public static void main(String[] args) throws SchedulerException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String returnstr = format.format(date);
        TestJob job = new TestJob();
        String job_name = "11";
//        try {
//            System.out.println("系统启动："+returnstr);
//
//        }

    }
}
