/**
 * Author: Madhu
 * User:madhu
 * Date:3/9/24
 * Time:3:53 PM
 * Project: parallel-kafka-consumer
 */

package io.madhu.kafkaConcurrentFlow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ExecutorsConfiguration {

    @Bean
    public ExecutorService fixedThreadPool(){
        return Executors.newFixedThreadPool(3);
    }
}
