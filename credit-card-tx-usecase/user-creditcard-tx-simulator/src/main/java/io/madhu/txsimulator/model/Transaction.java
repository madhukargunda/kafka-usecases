/**
 * Author: Madhu
 * User:madhu
 * Date:17/6/24
 * Time:7:17 PM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.model;

import io.madhu.txsimulator.constants.StoreType;
import io.madhu.txsimulator.constants.TransactionTypes;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
public class Transaction implements Serializable {
    /**
     * list of allowed transaction Types using the card
     */
    private TransactionTypes type;

    /**
     * User Card - CreditCard Number
     */
    private String cardNumber;

    /**
     * Unique UserId of the user
     */
    private String userId;
    private BigDecimal amount;
    private StoreType storeType;
    private ZonedDateTime timestamp;
}
