package org.tools;

import org.example.MyJobClass;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 *  <dependency>
 *             <groupId>org.quartz-scheduler</groupId>
 *             <artifactId>quartz</artifactId>
 *             <version>2.3.0</version>
 *         </dependency>
 */

public class Quartz {
    public static String quartz = "*/5 * * * * ?";

    public static void start () {
        try {
            // 创建调度器工厂
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();

            // 获取调度器实例
            Scheduler scheduler = schedulerFactory.getScheduler();

            // 定义jobDetail
            JobDetail jobDetail = newJob(MyJobClass.class).withIdentity("myJob", "group1").build();

            // 定义trigger
        Trigger trigger = newTrigger().withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(cronSchedule(quartz))
                .build();
//            Trigger trigger = newTrigger().withIdentity("myTrigger", "group1")
//                    .startNow()
//                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
//                    .build();

            // 将jobDetail和trigger注册到scheduler中
            scheduler.scheduleJob(jobDetail, trigger);

            // 开始调度
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
