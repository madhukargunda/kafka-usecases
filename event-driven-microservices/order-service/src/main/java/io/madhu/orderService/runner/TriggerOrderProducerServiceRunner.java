/**
 * Author: Madhu
 * User:madhu
 * Date:4/8/24
 * Time:9:11 AM
 * Project: event-driven-microservices
 */

package io.madhu.orderService.runner;

import io.madhu.baseDomains.model.Order;
import io.madhu.orderService.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class TriggerOrderProducerServiceRunner implements CommandLineRunner {

    @Autowired
    private OrderProducerService orderProducerService;

    @Override
    public void run(String... args) throws Exception {
        Order order = new Order();
        order.setName("FRUITS");
        order.setPrice(13.5d);
        IntStream.range(0, 10)
                .forEach(c -> {
                    orderProducerService.dispatch(order.buildOrderEvent());
                });
    }
}

