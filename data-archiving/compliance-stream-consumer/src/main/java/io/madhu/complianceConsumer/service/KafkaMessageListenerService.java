/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:10:31 AM
 * Project: compliance-stream-consumer
 */

package io.madhu.complianceConsumer.service;

import io.madhu.complianceConsumer.event.LegalArchivingEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Slf4j
@Profile("consumer-executor")
public class KafkaMessageListenerService {

    private static final String LEGAL_ARCHIVING = "legal-archiving";
    AtomicLong atomicLong = new AtomicLong();

    private final ExecutorService fixedThreadPool;

    private KafkaTemplate<String, LegalArchivingEvent> kafkaTemplate;

    public KafkaMessageListenerService(ExecutorService taskExecutor) {
        this.fixedThreadPool = taskExecutor;
    }

    @KafkaListener(topics = LEGAL_ARCHIVING)
    public void processLegalArchivingEvent(ConsumerRecord<String, LegalArchivingEvent> record) {
        log.info("Legal Archiving event processing here");
        fixedThreadPool.execute(() -> {
            try {
                this.process(record.value(), atomicLong.getAndIncrement());
            } catch (Exception e) {
                this.sendToDeadLetterTopic(record, e);
            }
        });
    }

    public void process(LegalArchivingEvent legalArchivingEvent, Long threadNumber) {// RestAPI Chaining initiate here
        log.info("Processing the Event {}", legalArchivingEvent);
    }

    private void sendToDeadLetterTopic(ConsumerRecord<String, LegalArchivingEvent> event, Exception e) {
        kafkaTemplate.send("dead-letter-topic", event.key(), event.value());
    }
}
