/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:12:34 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.repository;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;

public interface EmitterRepository {

    public void addOrUpdate(String memberId , SseEmitter sseEmitter);

    public void remove(String memberId);

    public Optional<SseEmitter> get(String memberId);
}
