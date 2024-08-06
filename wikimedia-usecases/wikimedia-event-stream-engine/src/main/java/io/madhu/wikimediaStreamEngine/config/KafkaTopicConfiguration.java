/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:1:41 PM
 * Project: wikimedia-event-stream-engine
 */

package io.madhu.wikimediaStreamEngine.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Slf4j
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic topic() {
        log.info("Creating the wiki-media Topic");
        return TopicBuilder.name("wiki-media").partitions(2).build();
    }
}
