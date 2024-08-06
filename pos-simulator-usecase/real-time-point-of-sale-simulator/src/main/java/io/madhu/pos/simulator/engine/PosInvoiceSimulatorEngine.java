/**
 * Author: Madhu
 * User:madhu
 * Date:21/7/24
 * Time:6:47 PM
 * Project: real-time-point-of-sale-simulator
 */

package io.madhu.pos.simulator.engine;

import io.madhu.pos.simulator.producer.RunnableInvoiceProducer;
import io.madhu.pos.simulator.service.KafkaDispatcherService;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.LongStream;

@Component
@Slf4j
public class PosInvoiceSimulatorEngine {

    private static final String TOPIC_NAME = "orders";
    @Autowired
    private KafkaDispatcherService kafkaDispatcherService;
    private static Integer numberOfThreads = 10;
    private ExecutorService workerThreadPool;

    public void start() {
        log.info("PosInvoiceSimulator Engine started.....");
        workerThreadPool = Executors.newFixedThreadPool(numberOfThreads);
        LongStream.range(1, numberOfThreads)
                .forEach(i -> {
                    workerThreadPool.submit(new RunnableInvoiceProducer(i, kafkaDispatcherService, TOPIC_NAME, 1000l));
                });
    }

    @PreDestroy
    public void destroy(){
       workerThreadPool.shutdown();
    }
}
