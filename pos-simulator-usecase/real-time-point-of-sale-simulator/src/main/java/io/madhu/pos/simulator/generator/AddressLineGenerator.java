/**
 * Author: Madhu
 * User:madhu
 * Date:24/6/24
 * Time:11:30 AM
 * Project: real-time-point-of-sale-simulator
 */

package io.madhu.pos.simulator.generator;

import com.github.javafaker.Faker;
import io.madhu.pos.simulator.beans.DeliveryAddress;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Random;

@Slf4j
public class AddressLineGenerator {

    private static final AddressLineGenerator INSTANCE = new AddressLineGenerator();
    private static Faker faker;

    private AddressLineGenerator() {
        log.info("Address Can not be created ");
    }

    public static AddressLineGenerator getInstance() {
//        if (Objects.isNull(INSTANCE)) {
//            INSTANCE = AddressLineGenerator.getInstance();
//        }
        faker = Faker.instance(new Random(28));
        return INSTANCE;
    }

    public static DeliveryAddress getNextAddress() {
        DeliveryAddress deliveryAddress = new DeliveryAddress();
        deliveryAddress.setAddressLine(faker.address().fullAddress());
        deliveryAddress.setCity(faker.address().city());
        deliveryAddress.setState(faker.address().state());
        deliveryAddress.setPinCode(faker.address().zipCode());
        deliveryAddress.setContactNumber(faker.phoneNumber().phoneNumber());
        log.info("Delivery Address {}");
        return deliveryAddress;
    }
}
