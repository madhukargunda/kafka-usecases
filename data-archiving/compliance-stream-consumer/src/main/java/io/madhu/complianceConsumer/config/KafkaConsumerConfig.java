/**
 * Author: Madhu
 * User:madhu
 * Date:1/8/24
 * Time:9:50 AM
 * Project: compliance-stream-consumer
 */

package io.madhu.complianceConsumer.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Slf4j
public class KafkaConsumerConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        log.info("kafkaProperties {}", kafkaProperties);
        //Map<String, Object> consumerProps = new HashMap<>();
        Map<String, Object> consumerProps = kafkaProperties.buildConsumerProperties(null);
//        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getConsumer().getBootstrapServers());
//        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());
//        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getKeyDeserializer());
//        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, kafkaProperties.getConsumer().getValueDeserializer());
//      Map<String, Object> props = kafkaProperties.buildConsumerProperties(null);
//      props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//      props.put("value.deserializer", "org.springframework.kafka.support.serializer.ErrorHandlingDeserializer");
//        props.put("spring.deserializer.value.delegate.class", "org.springframework.kafka.support.serializer.JsonDeserializer");
//        props.put("spring.json.trusted.packages", "io.madhu.complianceConsumer.event");
//        props.put("spring.json.value.default.type", "io.madhu.complianceConsumer.event.LegalArchivingEvent");
        return new DefaultKafkaConsumerFactory<>(consumerProps);
    }

    /**
     * Creates the three consumers
     *
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3); // Set the number of consumer threads container will be created
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }
}
