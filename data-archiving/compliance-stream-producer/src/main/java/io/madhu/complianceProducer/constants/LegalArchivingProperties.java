/**
 * Author: Madhu
 * User:madhu
 * Date:1/8/24
 * Time:6:56 AM
 * Project: compliance-stream-producer
 */

package io.madhu.complianceProducer.constants;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "legal.archiving")
@Data
public class LegalArchivingProperties {

    private Long scheduleRate;
    private List<String> supportedSites;
}
