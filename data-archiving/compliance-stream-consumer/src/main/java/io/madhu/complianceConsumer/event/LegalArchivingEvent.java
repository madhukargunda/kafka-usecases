/**
 * Author: Madhu
 * User:madhu
 * Date:1/8/24
 * Time:6:51 AM
 * Project: compliance-stream-producer
 */

package io.madhu.complianceConsumer.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LegalArchivingEvent implements Serializable {
    private String site;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Override
    public String toString() {
        return "LegalArchivingEvent{" +
                "site='" + site + '\'' +
                '}';
    }
}
