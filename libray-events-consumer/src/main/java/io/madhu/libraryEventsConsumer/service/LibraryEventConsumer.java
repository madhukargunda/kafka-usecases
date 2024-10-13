/**
 * Author: Madhu
 * User:madhu
 * Date:18/9/24
 * Time:9:51 AM
 * Project: libray-events-consumer
 */

package io.madhu.libraryEventsConsumer.service;


import io.madhu.libraryEventsConsumer.entity.LibraryEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * Two ways
 * <p>
 * 1.MessageListenerContainer interface implemented by two classes
 * KafkaMessageListenerContainer
 * ConcurrentMessageListenerContainer
 * <p>
 * Using the @EventListener Annotation
 */
@Slf4j
@Component
public class LibraryEventConsumer {

    @KafkaListener(topics = {"library-events"})
    public void onMessage(ConsumerRecord<Integer, LibraryEvent> consumerRecord) {
        log.info("Consumer Record Data ......{}.",consumerRecord);
    }
}
