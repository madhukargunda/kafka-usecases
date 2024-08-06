/**
 * Author: Madhu
 * User:madhu
 * Date:17/6/24
 * Time:7:56 PM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.generator;

import com.github.javafaker.Faker;
import io.madhu.txsimulator.config.CCTxSimulatorProperties;
import io.madhu.txsimulator.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class UsersGenerator {

    private final CCTxSimulatorProperties ccTxSimulatorProperties;

    /**
     * Creates the number of Users and associates the credit cards to each user
     *
     * @return
     */
    public List<User> getUsers() {
        Faker fake = new Faker();
        return IntStream.range(0, ccTxSimulatorProperties.getNumberOfUsers())
                .mapToObj(i -> {
                    return this.getUser();
                })
                .collect(Collectors.toList());
    }

    /**
     *
     * @return
     */
    public User getUser() {
        Faker fake = new Faker();
        User user = new User(fake.name().firstName(), fake.name().lastName());
        user.setId(fake.idNumber().valid());
        //UUID.randomUUID().toString());
        IntStream.range(0, ccTxSimulatorProperties.getNumberOfCards())
                .forEach(n -> user.add(fake.finance().creditCard()));
        return user;
    }
}
