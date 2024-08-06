/**
 * Author: Madhu
 * User:madhu
 * Date:8/7/24
 * Time:1:15 AM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.runner;


import io.madhu.txsimulator.generator.TransactionGenerator;
import io.madhu.txsimulator.model.Transaction;
import io.madhu.txsimulator.service.KafkaDispatcherService;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TransactionTask implements Runnable {

    private String userId;
    private String creditCardNumber;
    private KafkaDispatcherService kafkaDispatcherService;
    private TransactionGenerator transactionGenerator;
    private Long uniqueId;

    public TransactionTask(String userId, String creditCardNumber, KafkaDispatcherService kafkaDispatcherService,Long uniqueId) {
        log.info("TransactionTask Object created for user: {}, card: {}", userId, creditCardNumber);
        this.userId = userId;
        this.creditCardNumber = creditCardNumber;
        this.kafkaDispatcherService = kafkaDispatcherService;
        this.uniqueId = uniqueId;
    }

    @Override
    public void run() {
        //System.out.println(String.format("User: %s performing transaction with card: %s", this.userId, this.creditCardNumber));
        Transaction transaction = transactionGenerator.createTransaction(this.creditCardNumber, this.userId);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            log.error("Exception occurred while performing transaction for user: {}, card: {}", userId, creditCardNumber, e);
        }
        // kafkaDispatcherService.dispatch(transaction);
    }
}
