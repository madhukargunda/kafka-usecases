/**
 * Author: Madhu
 * User:madhu
 * Date:1/8/24
 * Time:6:48 AM
 * Project: compliance-stream-producer
 */

package io.madhu.complianceProducer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class SchedulerConfig {

    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        return Executors.newScheduledThreadPool(4);
    }
}

