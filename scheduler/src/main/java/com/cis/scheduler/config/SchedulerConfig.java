package com.cis.scheduler.config;

import com.cis.scheduler.jobs.QueryJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(QueryJob.class) // Sua classe de job
                .withIdentity("yourJob") // Identificação da job
                .storeDurably() // Permite que a job seja armazenada mesmo que não tenha triggers ativas
                .build();
    }

    @Bean
    public Trigger jobTrigger(JobDetail jobDetail) {
        // Usando SimpleScheduleBuilder diretamente
        ScheduleBuilder<SimpleTrigger> scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInHours(1) // Define a frequência do agendamento
                .repeatForever(); // Repetição infinita

        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetail)
                .withIdentity("yourTrigger") // Identificação do trigger
                .withSchedule(scheduleBuilder) // Adiciona o agendamento
                .build();
    }
}
