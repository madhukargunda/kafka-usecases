/**
 * Author: Madhu
 * User:madhu
 * Date:8/9/24
 * Time:11:44 AM
 * Project: library-events-producer
 */

package io.madhu.libraryEventsProducer.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class LibraryEventsKafkaConfiguration {

    @Bean
    public NewTopic libraryEventTopic() {
        return TopicBuilder.name("library-event")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
