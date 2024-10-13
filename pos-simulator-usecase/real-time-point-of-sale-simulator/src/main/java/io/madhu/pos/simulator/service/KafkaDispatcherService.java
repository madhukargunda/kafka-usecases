/**
 * Author: Madhu
 * User:madhu
 * Date:21/7/24
 * Time:6:03 PM
 * Project: real-time-point-of-sale-simulator
 */

package io.madhu.pos.simulator.service;

import io.madhu.pos.simulator.beans.PosInvoice;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class KafkaDispatcherService {

    @Autowired
    KafkaTemplate<String, PosInvoice> kafkaTemplate;

    AtomicLong atomicLong = new AtomicLong();

    private static void accept(SendResult<String, PosInvoice> results, Throwable exception) {
        log.info("Results Received---->");
        if (Objects.nonNull(results)) {
            RecordMetadata recordMetadata = results.getRecordMetadata();
            log.info("Event published successfully published to partition {}, topic {}", recordMetadata.partition(), recordMetadata.topic());
        } else if (Objects.nonNull(exception)) {
            log.info("Event not published due the exception {}", exception);
        }
    }


    public void dispatch(String key, String topicName, PosInvoice posInvoice) {
        log.info("PosInvoice dispatched to kafka broker..... {} -> {} {} ", posInvoice,posInvoice.getInvoiceNumber(), atomicLong.getAndIncrement());
        CompletableFuture<SendResult<String, PosInvoice>> sendResultCompletableFuture = kafkaTemplate.send(topicName, key, posInvoice);
        sendResultCompletableFuture.whenComplete(KafkaDispatcherService::accept);
        kafkaTemplate.flush();
    }
}
