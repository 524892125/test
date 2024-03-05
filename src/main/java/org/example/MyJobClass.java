package org.example;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.tools.StringToFileWriter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class MyJobClass implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        // 获取当前时间戳对应的Instant对象
        Instant now = Instant.now();

        // 将Instant转换为指定时区的LocalDateTime
        LocalDateTime localDateTime = now.atZone(ZoneId.systemDefault()).toLocalDateTime();

        // 创建一个格式器来按照年月日和毫秒的格式输出
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        // 格式化输出日期时间
        String formattedDateTime = localDateTime.format(formatter);
        try {
            StringToFileWriter.appendStringToFile(formattedDateTime + "\n", "time.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("当前时间（年月日时分秒毫秒）：" + formattedDateTime);

//        // 获取并打印年、月、日和毫秒
//        int year = localDateTime.getYear();
//        int month = localDateTime.getMonthValue();
//        int day = localDateTime.getDayOfMonth();
//        long millis = localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli() % 1000;
//
//        System.out.printf("当前时间：年 - %d, 月 - %d, 日 - %d, 毫秒 - %d%n", year, month, day, millis);
    }
}
