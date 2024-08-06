/**
 * Author: Madhu
 * User:madhu
 * Date:24/6/24
 * Time:8:30 PM
 * Project: sse-user-events
 */

package io.madhu.sseUserEvent.service;

import io.madhu.sseUserEvent.repository.EmittersRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
@EnableScheduling
public class RandomUserEmitterService {

    private static final String RANDOM_USER_EVENT = "SSE-USER-EVENT";

    private CopyOnWriteArrayList<SseEmitter> emittersList = new CopyOnWriteArrayList<>();

    @Autowired
    private RandomUserRestService randomUserRestService;

    @Autowired
    private EmittersRepository emittersRepository;

    /**
     * Creates the Emitter Object specific to user
     * and associated with eventType
     *
     * @param eventType
     * @return
     */
    public SseEmitter createEmitter(String eventType) {
        log.info("User Subscribed to EvenType {}", eventType);
        SseEmitter sseEmitter = new SseEmitter();
        emittersRepository.add(eventType, sseEmitter);
        sseEmitter.onCompletion(() -> emittersList.remove(sseEmitter));
        sseEmitter.onError((ex) -> emittersList.remove(sseEmitter));
        sseEmitter.onTimeout(() -> emittersList.remove(sseEmitter));
        return sseEmitter;
    }

    @Scheduled(fixedDelay = 5000)
    private void emitRandomUserEvents() {
        log.info("produceRandomUserData triggered every 5000 ms {}");
        for (SseEmitter emitter : emittersRepository.get(RANDOM_USER_EVENT)) {
            try {
                emitter.send(randomUserRestService.getRandomUser(), MediaType.APPLICATION_JSON);
                // emitter.send(new ServerSentEvent(1,"SSE-USER-EVENT", Duration.ofSeconds(5),"SSE-USER-EVENT",user));
            } catch (IOException | IllegalStateException e) {
                emitter.completeWithError(e);
                emittersList.remove(emitter);
            }
        }
    }

    //Model -2
    private void emitPingEvents() {
        CopyOnWriteArrayList<SseEmitter> sseEmitters = emittersRepository.get("sse-user-events");
        for (SseEmitter sseEmitter : sseEmitters) {
            this.task(sseEmitter);
        }
    }

    @SneakyThrows
    private SseEmitter task(SseEmitter sseEmitter) {
        AtomicInteger integer = new AtomicInteger();
        Runnable task = () -> {
            emitPeriodicEvents(sseEmitter, integer);
        };
        new Thread(task).start();
        return sseEmitter;
    }

    private void emitPeriodicEvents(SseEmitter sseEmitter, AtomicInteger integer) {
        while (true) {
            sseEmitter.onCompletion(() -> emittersList.remove(sseEmitter));
            sseEmitter.onError((ex) -> emittersList.remove(sseEmitter));
            ServerSentEvent<String> periodicEventSignal = ServerSentEvent.<String>builder().event("PERIODIC_EVENT_SIGNAL")
                    .id(String.valueOf(integer.getAndIncrement()))
                    .data(String.format("Event Occurred %s -> ", LocalDateTime.now().toString()))
                    .build();
            sseEmitter.onTimeout(() -> emittersList.remove(sseEmitter));
            try {
                sseEmitter.send(Mono.just(periodicEventSignal), MediaType.APPLICATION_JSON);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
