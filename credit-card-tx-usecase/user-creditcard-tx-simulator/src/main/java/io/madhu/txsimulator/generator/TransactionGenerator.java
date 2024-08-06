/**
 * Author: Madhu
 * User:madhu
 * Date:24/6/24
 * Time:6:19 PM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.generator;


import io.madhu.txsimulator.constants.StoreType;
import io.madhu.txsimulator.constants.TransactionTypes;
import io.madhu.txsimulator.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Random;

@Slf4j
@Component
public class TransactionGenerator {

    private static final Random random = new Random();

    public Transaction createTransaction(String creditCard , String userId) {
        //log.info("User {} Performing the transaction with the  ,CreditCardNumber {} and the user", userId, creditCard);
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(50 + (1000 - 100) * random.nextDouble())); //fake formula
        transaction.setStoreType(StoreType.values()[this.random.nextInt(StoreType.values().length)]);
        transaction.setUserId(userId);
        transaction.setType(TransactionTypes.values()[this.random.nextInt(TransactionTypes.values().length)]);
        transaction.setTimestamp(ZonedDateTime.now());
        transaction.setCardNumber(creditCard);
        return transaction;
    }
}
