/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:12:32 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.service;


import io.madhu.realtimeNotifications.repository.EmitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.Duration;
import java.util.Set;

@Service
@Slf4j
public class EmitterService {

    private final EmitterRepository emitterRepository;

    private final Long eventTimeOut;

    @Autowired
    public EmitterService(EmitterRepository emitterRepository, @Value("${spring.event.timeout}") Long eventTimeOut) {
        this.emitterRepository = emitterRepository;
        this.eventTimeOut = eventTimeOut;
    }

    /**
     * SseEmitter is Non-Reactive
     * Developer has to maintain the LifeCycle
     * TimeOut value representing in milliseconds
     * @param memberId
     * @return
     */
    public SseEmitter createEmitter(String memberId) {
        log.info("SseEmitter created for the memberId : {}", memberId);
        SseEmitter sseEmitter = new SseEmitter(Duration.ofMinutes(5).toMillis());
        sseEmitter.onTimeout(() -> emitterRepository.remove(memberId));
        sseEmitter.onCompletion(() -> emitterRepository.remove(memberId));
        sseEmitter.onError((ex) -> emitterRepository.remove(memberId));
        emitterRepository.addOrUpdate(memberId, sseEmitter);
        return sseEmitter;
    }
}
