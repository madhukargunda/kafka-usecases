/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:10:32 PM
 * Project: event-driven-microservices
 */

package io.madhu.orderService.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class OrderTopicConfiguration {

    @Value("${spring.topic.name}")
    private String topicName;

    @Bean
    public NewTopic newTopic() {
        return TopicBuilder.name(topicName).partitions(1).build();
    }
}
