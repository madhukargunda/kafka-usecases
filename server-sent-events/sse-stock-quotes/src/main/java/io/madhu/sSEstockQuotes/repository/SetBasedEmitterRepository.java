/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:12:37 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.sSEstockQuotes.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


@Slf4j
@Repository
@RequiredArgsConstructor
public class SetBasedEmitterRepository {

    private final CopyOnWriteArraySet<SseEmitter> emitterSet = new CopyOnWriteArraySet<>();

    public void addOrUpdate(SseEmitter sseEmitter) {
        emitterSet.add(sseEmitter);
    }

    public void remove(SseEmitter sseEmitter) {
        emitterSet.remove(sseEmitter);
    }

    public Set<SseEmitter> getAll() {
        return this.emitterSet;
    }
}
