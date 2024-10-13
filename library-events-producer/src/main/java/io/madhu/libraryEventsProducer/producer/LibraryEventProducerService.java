/**
 * Author: Madhu
 * User:madhu
 * Date:8/9/24
 * Time:12:36 PM
 * Project: library-events-producer
 */

package io.madhu.libraryEventsProducer.producer;

import io.madhu.libraryEventsProducer.events.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class LibraryEventProducerService {

    @Autowired
    private NewTopic topic;

    private final KafkaTemplate<String, LibraryEvent> kafkaTemplate;

    public LibraryEventProducerService(KafkaTemplate<String, LibraryEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(LibraryEvent libraryEvent) {
        CompletableFuture<SendResult<String, LibraryEvent>> sendResultCompletableFuture = kafkaTemplate.send(topic.name(), libraryEvent);
        sendResultCompletableFuture.whenComplete((sedResult, throwable) -> {
            log.info("Received the results");
            if (!Objects.isNull(throwable)) {
                log.info("Error sending the message to kafka topic {}",throwable.getMessage());
            } else {
                log.info("Message send successfully to Topic: {} Partition: {} ", sedResult.getRecordMetadata().partition());
            }
        });
    }
}
