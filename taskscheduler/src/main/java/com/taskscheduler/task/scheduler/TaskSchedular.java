package com.taskscheduler.task.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TaskSchedular {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Scheduled(fixedRate = 30000)
    public void invokeTask(){
        System.out.println("Task Executing with fixedrate "+ dateTimeFormatter.format(LocalDateTime.now()));
    }
//    @Scheduled(cron = "*/10 * * * * ?") -> Every Minute
    @Scheduled(cron = "11 42 16 * * ?")
    public void performTaskUsingCron() {
        System.out.println("Task executed with cron at 4:42:11 " + dateTimeFormatter.format(LocalDateTime.now()));
    }
}
