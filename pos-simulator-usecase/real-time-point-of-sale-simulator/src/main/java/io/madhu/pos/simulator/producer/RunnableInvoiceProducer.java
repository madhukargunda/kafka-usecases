/**
 * Author: Madhu
 * User:madhu
 * Date:8/7/24
 * Time:11:29 PM
 * Project: real-time-point-of-sale-simulator
 */

package io.madhu.pos.simulator.producer;

import io.madhu.pos.simulator.beans.PosInvoice;
import io.madhu.pos.simulator.generator.InvoiceGenerator;
import io.madhu.pos.simulator.service.KafkaDispatcherService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class RunnableInvoiceProducer implements Runnable {

    private static final AtomicBoolean stopper = new AtomicBoolean(Boolean.FALSE);
    private KafkaDispatcherService kafkaDispatcherService;
    private String topicName;
    private InvoiceGenerator invoiceGenerator;
    private Long producerSpeedInMills;
    private Long iThreadId;

    public RunnableInvoiceProducer(Long iThreadId, KafkaDispatcherService kafkaDispatcherService, String topicName, long producerSpeedInMills) {
        log.info("Creating the RunnableInvoiceProducer............");
        this.kafkaDispatcherService = kafkaDispatcherService;
        this.invoiceGenerator = InvoiceGenerator.getInstance();
        this.producerSpeedInMills = producerSpeedInMills;
        this.iThreadId = iThreadId;
    }

    @Override
    @SneakyThrows
    public void run() {
        log.info("Producer Thread Id-{} started running......", iThreadId);
        while (!stopper.get()) {
           // log.info("Generating the POSInvoice object................");
            PosInvoice nextInvoice = this.invoiceGenerator.getNextInvoice();
            kafkaDispatcherService.dispatch(nextInvoice.getStoreID(), this.topicName, nextInvoice);
            TimeUnit.MILLISECONDS.sleep(producerSpeedInMills);
        }
    }

    public void shutDown() {
        if (stopper.get()) {
            stopper.set(Boolean.TRUE);
        }
    }
}
