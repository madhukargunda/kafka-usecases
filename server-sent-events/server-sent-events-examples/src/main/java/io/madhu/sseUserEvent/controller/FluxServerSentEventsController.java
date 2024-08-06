/**
 * Author: Madhu
 * User:madhu
 * Date:7/7/24
 * Time:2:42 PM
 * Project: server-sent-events-examples
 */

package io.madhu.sseUserEvent.controller;


import io.madhu.sseUserEvent.service.RandomUserRestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@Slf4j
@AllArgsConstructor
public class FluxServerSentEventsController {

    private final RandomUserRestService randomUserRestService;

    @GetMapping(value = "/flux-server-sent" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<?>> getEvents() {
        log.info("Flux ServerSentEvent getEvents method triggered");
        return Flux.interval(Duration.ofSeconds(5))
                .map(tick -> ServerSentEvent.builder()
                        .event("FLUX-EVENT-5SECONDS")
                        .data("Flux-Event " + LocalDateTime.now())
                        .build());
    }

    @GetMapping(value = "/user-server-sent" ,produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<?>> getUserEvents() {
        log.info("Flux ServerSentEvent getUserEvents method triggered");
        return Flux.interval(Duration.ofSeconds(5))
                .map(tick -> ServerSentEvent.builder()
                        .event("FLUX-EVENT-5SECONDS")
                        .data(randomUserRestService.getRandomUser())
                        .build());
    }
}
