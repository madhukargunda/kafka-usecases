/**
 * Author: Madhu
 * User:madhu
 * Date:1/8/24
 * Time:10:37 AM
 * Project: compliance-stream-consumer
 */

package io.madhu.complianceConsumer.service;


import io.madhu.complianceConsumer.event.LegalArchivingEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("consumer-group")
public class KafkaConsumerGroupConfigListenerService {

    private static final String LEGAL_ARCHIVING = "legal-archiving";

    @KafkaListener(id = "legal-archiving-consumer-1", groupId = "legal-archiving-groupA",clientIdPrefix = "Group -1")
//            ,topicPartitions = {@TopicPartition(topic = LEGAL_ARCHIVING, partitions = {"0"})})
    public void consumerPartition0(ConsumerRecord<String, LegalArchivingEvent> consumerRecord, Acknowledgment acknowledgment) {
        log.info("Consumed message: {}", consumerRecord.value());
        try {
            // RestAPI Chaining here ,Call the API Chain Service here
            Thread.sleep(3000); // Adjust processing time as necessary
            acknowledgment.acknowledge(); // Manually acknowledge the message
        } catch (InterruptedException e) {
            log.error("Error processing message", e);
            Thread.currentThread().interrupt();
        }

    }

    @KafkaListener(id = "legal-archiving-consumer-2", groupId = "legal-archiving-groupB", topics = LEGAL_ARCHIVING,clientIdPrefix = "Group -2")
    public void consumerPartition1(ConsumerRecord<String, LegalArchivingEvent> consumerRecord, Acknowledgment acknowledgment) {
        log.info("Consumed messages:1 {}", consumerRecord.value());
        try {
            // RestAPI Chaining here
            Thread.sleep(3000); // Adjust processing time as necessary
            acknowledgment.acknowledge(); // Manually acknowledge the message
        } catch (InterruptedException e) {
            log.error("Error processing message", e);
            Thread.currentThread().interrupt();
        }
    }

    @KafkaListener(id = "legal-archiving-consumer-3", groupId = "legal-archiving-groupC", topics = LEGAL_ARCHIVING,clientIdPrefix = "Group 3")
    public void consumerPartition3(ConsumerRecord<String, LegalArchivingEvent> consumerRecord, Acknowledgment acknowledgment) {
        log.info("Consumed message3: {}", consumerRecord.value());
        try {
            // RestAPI Chaining here
            Thread.sleep(3000); // Adjust processing time as necessary
            acknowledgment.acknowledge(); // Manually acknowledge the message
        } catch (InterruptedException e) {
            log.error("Error processing message", e);
            Thread.currentThread().interrupt();
        }
    }
}
