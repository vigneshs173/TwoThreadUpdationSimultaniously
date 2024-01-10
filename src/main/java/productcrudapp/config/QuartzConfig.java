package productcrudapp.config;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import productcrudapp.controller.ApiHitJob;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail apiHitJobDetail() {
        return JobBuilder.newJob(ApiHitJob.class)
                .withIdentity("apiHitJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger apiHitJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10) // Repeat every 60 seconds
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(apiHitJobDetail())
                .withIdentity("apiHitJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}