package com.test.quartz;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 学习使用quartz
 * Created by mrf on 2016/3/3.
 */
public class HelloQuartz  implements Job{
    Logger logger = LoggerFactory.getLogger(HelloQuartz.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        logger.debug("Hellow Quarz! - executing its job at "+DateFormat.getDateTimeInstance().format(new Date())+" by "+jobExecutionContext.getTrigger().getKey() + "\n \n");
    }
}

class BootstrapQuartz{
    private static Logger logger = LoggerFactory.getLogger(BootstrapQuartz.class);

    public static void main(String[] args) {
        try {
            //获取scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

            //具体任务
            JobDetail jobDetail = JobBuilder.newJob(HelloQuartz.class).withIdentity("job1","group1").build();

            //触发时间
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(5).repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1")
                    .startNow().withSchedule(simpleScheduleBuilder).build();

            //交由scheduler安排触发
            scheduler.scheduleJob(jobDetail,trigger);

            //exe 5s
            Thread.sleep(10000);

            //stop
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("exe job error:"+e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
