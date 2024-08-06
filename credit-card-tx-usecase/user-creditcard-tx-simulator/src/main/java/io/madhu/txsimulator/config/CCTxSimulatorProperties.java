/**
 * Author: Madhu
 * User:madhu
 * Date:8/7/24
 * Time:12:05 PM
 * Project: user-creditcard-tx-simulator
 */

package io.madhu.txsimulator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@ConfigurationProperties(prefix = "spring.user")
@Data
@Component
public class CCTxSimulatorProperties {
    private Integer transactionsPerUser;
    private Integer numberOfUsers;
    private Integer numberOfCards;
}
