/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:11:24 PM
 * Project: event-driven-microservices
 */

package io.madhu.emailService.service;

import io.madhu.baseDomains.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumeService {

    @KafkaListener(
            topics = "${spring.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(OrderEvent orderEvent) {
        log.info(String.format("OrderEvent Received in Stock Service => %s", orderEvent));
    }
}
