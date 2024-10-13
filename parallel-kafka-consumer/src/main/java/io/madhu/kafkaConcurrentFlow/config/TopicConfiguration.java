/**
 * Author: Madhu
 * User:madhu
 * Date:3/9/24
 * Time:11:19 AM
 * Project: parallel-kafka-consumer
 */

package io.madhu.kafkaConcurrentFlow.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic userTopic() {
        return TopicBuilder.name("users-topic").partitions(1).build();
    }
}
