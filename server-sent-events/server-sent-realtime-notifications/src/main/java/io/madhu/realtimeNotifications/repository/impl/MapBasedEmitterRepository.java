/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:12:37 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.repository.impl;

import io.madhu.realtimeNotifications.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@Repository
@RequiredArgsConstructor
public class MapBasedEmitterRepository implements EmitterRepository {

    private final ConcurrentHashMap<String,SseEmitter> emitterMap = new ConcurrentHashMap<>();

    @Override
    public void addOrUpdate(String memberId, SseEmitter sseEmitter) {
      emitterMap.put(memberId,sseEmitter);
    }

    @Override
    public void remove(String memberId) {
       emitterMap.computeIfPresent(memberId , (k,v) -> emitterMap.remove(memberId));
    }

    @Override
    public Optional<SseEmitter> get(String memberId) {
        return Optional.ofNullable(emitterMap.get(memberId));
    }
}
