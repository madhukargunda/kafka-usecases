/**
 * Author: Madhu
 * User:madhu
 * Date:22/7/24
 * Time:12:18 AM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.producer;

import io.madhu.txsimulator.generator.TransactionGenerator;
import io.madhu.txsimulator.generator.UsersGenerator;
import io.madhu.txsimulator.model.Transaction;
import io.madhu.txsimulator.model.User;
import io.madhu.txsimulator.service.KafkaDispatcherService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TransactionTaskFixedUsersProducer {

    @Autowired
    KafkaDispatcherService kafkaDispatcherService;

    @Autowired
    private UsersGenerator usersGenerator;

    @Autowired
    private TransactionGenerator transactionGenerator;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(15);

    @SneakyThrows
    public void produce() {
        log.info("Producers produce method invoked............. ");
        List<User> users = usersGenerator.getUsers();
        log.info("The users list size is  {} ", users.size());
        Random random = new Random();
        users.forEach(user -> {
            for (String creditCardNumber : user.getCreditCards()) {
                executorService.scheduleWithFixedDelay(() -> {
                    log.info("User: {} performing transaction with card: {} ", user.getId(), creditCardNumber);
                    Transaction transaction = transactionGenerator.createTransaction(creditCardNumber, user.getId());
                 //   kafkaDispatcherService.dispatch(transaction);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        log.error("Exception occurred while performing transaction for user: {}, card: {}", user.getId(), creditCardNumber, e);
                    }
                }, 0, 5, TimeUnit.SECONDS);
              // executorService.scheduleAtFixedRate(new TransactionTask(user.getId(),creditCardNumber,this.kafkaDispatcherService,random.nextLong()),0,5,TimeUnit.SECONDS);
            }
        });

//        executorService.schedule(() -> {
//            System.out.println("Task is running after 5 seconds.");
//        }, 5, TimeUnit.SECONDS);
//
//        executorService.scheduleAtFixedRate(() -> {
//            System.out.println("Task is running repeatedly every 2 seconds.");
//        }, 0, 2, TimeUnit.SECONDS);
    }

}
