package com.ldf.demo.config;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {

    private  static final SimpleDateFormat dataFromat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron="0 54 09 * * ?")
    public void cron() throws Exception{
        System.out.println("现在时间：{}"+dataFromat.format(new Date()));
    }
}
