/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:10:16 AM
 * Project: compliance-stream-consumer
 */

package io.madhu.complianceConsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ExecutorPoolConfig {

    @Bean
    public ExecutorService fixedThreadPool() {
        return Executors.newFixedThreadPool(6);
    }
}
