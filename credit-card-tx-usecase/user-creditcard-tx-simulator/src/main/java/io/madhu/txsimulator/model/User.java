/**
 * Author: Madhu
 * User:madhu
 * Date:17/6/24
 * Time:7:59 PM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Data
@ToString
@AllArgsConstructor
public class User implements Serializable {

    /**
     * Id of the user
     */
    private String id;

    /**
     * Number of CreditCards User holds
     */
    private Set<String> creditCards;

    /**
     * Using fake api generates the firstName;
     */
    private String firstName;

    /**
     * using fakse api generates the lastName;
     */
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.creditCards = new HashSet();
    }

    public void add(String creditCard){
        this.creditCards.add(creditCard);
    }
}
