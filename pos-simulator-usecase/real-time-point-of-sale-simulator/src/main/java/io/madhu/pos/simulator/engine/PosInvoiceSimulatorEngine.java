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
import java.util.concurrent.ThreadFactory;
import java.util.stream.LongStream;

@Component
@Slf4j
public class PosInvoiceSimulatorEngine {

    private static final String TOPIC_NAME = "pos-invoice";
    @Autowired
    private KafkaDispatcherService kafkaDispatcherService;
    private static Integer numberOfThreads = 10;
    private ExecutorService workerThreadPool;

    public void start() {
        customThreadFactory();
        log.info("PosInvoiceSimulator Engine started.....");
        workerThreadPool = Executors.newFixedThreadPool(numberOfThreads,customThreadFactory());
        LongStream.range(1, 10)
                .forEach(i -> {
                    workerThreadPool.submit(new RunnableInvoiceProducer(i, kafkaDispatcherService, TOPIC_NAME, 1000l));
                });
    }

    private static ThreadFactory customThreadFactory() {
        // Array of thread names
        String[] shopNames = {"Walmart-Shop-", "Ebay-Shop-", "Mustafa-Shop-", "FairPrice-Shop", "Amazon-Shop-", "Ebay-Shop-","FlipKart-Shop-","Lazada-Shop","Shopee-shop","Ali-Express-Shop"};
        // Custom ThreadFactory
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            private int count = 0;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName(shopNames[count % shopNames.length]); // Assign name from shopNames array
                count++;
                return thread;
            }
        };
        return namedThreadFactory;
    }

    @PreDestroy
    public void destroy(){
       workerThreadPool.shutdown();
    }
}
