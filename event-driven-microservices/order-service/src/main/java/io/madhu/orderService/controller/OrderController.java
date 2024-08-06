/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:10:50 PM
 * Project: event-driven-microservices
 */

package io.madhu.orderService.controller;

import io.madhu.baseDomains.model.Order;
import io.madhu.baseDomains.model.OrderEvent;
import io.madhu.orderService.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderProducerService producerService;

    @PostMapping("/orders")
    public ResponseEntity<?> order(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());
        OrderEvent orderEvent = order.buildOrderEvent();
        producerService.dispatch(orderEvent);
        return new ResponseEntity<>(HttpStatusCode.valueOf(201));
    }
}
