/**
 * Author: Madhu
 * User:madhu
 * Date:17/6/24
 * Time:9:49 PM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.producer;

import io.madhu.txsimulator.generator.TransactionGenerator;
import io.madhu.txsimulator.generator.UsersGenerator;
import io.madhu.txsimulator.model.User;
import io.madhu.txsimulator.runner.TransactionTask;
import io.madhu.txsimulator.service.KafkaDispatcherService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@Slf4j
public class TransactionTaskProducer {

    @Autowired
    KafkaDispatcherService kafkaDispatcherService;

    @Autowired
    private UsersGenerator usersGenerator;

    @Autowired
    private TransactionGenerator transactionGenerator;

    private AtomicBoolean stopper = new AtomicBoolean(Boolean.FALSE);

    // private ScheduledExecutorService executorService =  Executors.newScheduledThreadPool(10);

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @SneakyThrows
    public void produce() {
        log.info("Producers produce method invoked....... ");
        while (!stopper.get()) {
            User user = usersGenerator.getUser();
            log.info("User Generated {} ", user.getId());
            user.getCreditCards()
                    .forEach(creditCard -> {
                        executorService.submit(new TransactionTask(user.getId(), creditCard, kafkaDispatcherService, 1l));
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

}
