/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:10:36 PM
 * Project: event-driven-microservices
 */

package io.madhu.orderService.service;

import io.madhu.baseDomains.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducerService {

    @Autowired
    private NewTopic newTopic;

    @Autowired
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void dispatch(OrderEvent orderEvent) {
        log.info("OrderEvent produced {}", orderEvent);
        kafkaTemplate.send(buildMessage(orderEvent));
    }

    private Message<OrderEvent> buildMessage(OrderEvent orderEvent) {
        log.info("Build Message orderEvent {}", orderEvent);
        Message<OrderEvent> message = MessageBuilder.withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();
        return message;
    }
}
