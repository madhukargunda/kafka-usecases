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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaDispatcherService {

    @Autowired
    KafkaTemplate<String, PosInvoice> kafkaTemplate;

    public void dispatch(String key, String topicName, PosInvoice posInvoice) {
        log.info("PosInvoice dispatched to kafka broker.....{}", posInvoice);
       // kafkaTemplate.send(topicName, key, posInvoice);
    }
}
