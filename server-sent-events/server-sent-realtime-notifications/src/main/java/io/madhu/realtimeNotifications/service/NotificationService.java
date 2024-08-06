/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:3:43 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.service;

import io.madhu.realtimeNotifications.model.EventDto;
import org.springframework.http.codec.ServerSentEvent;

public interface NotificationService {
    public void sendNotification(String memberId, EventDto serverSentEvent);
}
