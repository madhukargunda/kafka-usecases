/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:5:31 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventDto implements Serializable {

    /**
     * memberId of the user
     */
    private String memberId;

    /**
     * Type of event published
     */
    private String type;
    private Long eventId;
    private Map<String, Object> data;
}
