/**
 * Author: Madhu
 * User:madhu
 * Date:3/9/24
 * Time:12:22 PM
 * Project: parallel-kafka-consumer
 */

package io.madhu.kafkaConcurrentFlow.config;

import io.madhu.kafkaConcurrentFlow.bo.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.*;


/**
 * Logic Customized
 */
@Configuration
public class ConsumerConfiguration {

    @Autowired
    KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        // Map<String, Object> consumerProps = kafkaProperties.buildConsumerProperties(null);
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "user-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class.getName());
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, StringDeserializer.class.getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, User.class.getName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaConsumer<String, String> kafkaConsumer() {
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(kafkaProperties.buildConsumerProperties(null));
        kafkaConsumer.subscribe(Collections.singleton("users-topic"));
        return kafkaConsumer;
    }

    /**
     * The ConcurrentKafkaListenerContainerFactory is a key component in Spring Kafka that is used to
     * create Kafka listener containers. These containers are responsible for handling the consumption
     * of messages from Kafka topics. The primary purpose of ConcurrentKafkaListenerContainerFactory is
     * to enable the creation of concurrent consumers,
     * allowing multiple threads to consume messages in parallel from one or more Kafka topics.
     *
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(1);  // Number of concurrent threads
        // Set manual acknowledgment mode
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        // Error handling with retry
        factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(1000L, 3L))); // Retry 3 times with 1-second delay
        return factory;
    }
}
