/**
 * Author: Madhu
 * User:madhu
 * Date:20/6/24
 * Time:6:10 PM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.service;

import io.madhu.txsimulator.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaDispatcherService {

    @Autowired
    private KafkaTemplate<Long, Transaction> kafkaTemplate;

    /**
     * Dispathcing the CreditCard Transactions to
     *
     * @param transaction
     */
    public void dispatch(Transaction transaction) {
        log.info("Publishing Transaction to Kafka broker ..........");
        CompletableFuture<SendResult<Long, Transaction>> resultCompletableFuture = kafkaTemplate.send(transaction.getUserId(), transaction);
        log.info("Transaction message sent to topic");
        resultCompletableFuture.whenComplete((result, exception) -> {
            RecordMetadata recordMetadata = result.getRecordMetadata();
            log.info("RecordMetadata {} ", recordMetadata.offset());
        });
    }
}
