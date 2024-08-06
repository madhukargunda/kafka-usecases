/**
 * Author: Madhu
 * User:madhu
 * Date:3/8/24
 * Time:10:21 PM
 * Project: event-driven-microservices
 */

package io.madhu.baseDomains.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    private String message;
    private String status;
    private Order order;}

