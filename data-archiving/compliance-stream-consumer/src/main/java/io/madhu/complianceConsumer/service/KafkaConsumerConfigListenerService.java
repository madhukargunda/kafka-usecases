/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:3:13 PM
 * Project: compliance-stream-consumer
 */

package io.madhu.complianceConsumer.service;

import io.madhu.complianceConsumer.event.LegalArchivingEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class KafkaConsumerConfigListenerService {

    private static final String LEGAL_ARCHIVING = "legal-archiving";

    @Autowired
    private KafkaProperties kafkaProperties;

    @KafkaListener(topics = LEGAL_ARCHIVING, groupId = "legal-archiving")
    public void processLegalArchivingEvent(ConsumerRecord<String, LegalArchivingEvent> record) {
        log.info("Legal Archiving event processing here {} {}", record.key(), record.value());
    }

    public void process(LegalArchivingEvent legalArchivingEvent) throws InterruptedException {// RestAPI Chaining initiate here
        log.info("Processing Thread {} the LegalArchiving Event {}", Thread.currentThread().getName(), legalArchivingEvent);
        TimeUnit.SECONDS.sleep(3);
    }
}
