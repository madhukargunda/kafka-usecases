/**
 * Author: Madhu
 * User:madhu
 * Date:25/6/24
 * Time:10:42 AM
 * Project: sse-user-events
 */

package io.madhu.sseUserEvent.repository;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class EmittersRepository {

    private Map<String, CopyOnWriteArrayList<SseEmitter>> emittersMap;

    public EmittersRepository() {
        emittersMap = new ConcurrentHashMap<>(3);
    }

    public void add(String key, SseEmitter sseEmitter) {
        emittersMap.putIfAbsent(key, new CopyOnWriteArrayList<>()).add(sseEmitter);
    }

    public CopyOnWriteArrayList<SseEmitter> get(String key) {
        return emittersMap.computeIfAbsent(key, (k) -> new CopyOnWriteArrayList<>());
    }
}
