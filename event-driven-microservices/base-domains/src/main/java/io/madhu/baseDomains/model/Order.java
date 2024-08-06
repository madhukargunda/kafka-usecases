/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:10:19 PM
 * Project: event-driven-microservices
 */

package io.madhu.baseDomains.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String orderId;
    private String name;
    private int qty;
    private double price;

    public OrderEvent buildOrderEvent() {
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(this);
        orderEvent.setMessage("PENDING");
        orderEvent.setMessage("Order status is pending");
        return orderEvent;
    }
}
