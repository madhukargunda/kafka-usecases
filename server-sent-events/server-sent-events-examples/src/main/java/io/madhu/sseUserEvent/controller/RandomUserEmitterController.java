/**
 * Author: Madhu
 * User:madhu
 * Date:24/6/24
 * Time:7:19 PM
 * Project: sse-user-events
 */

package io.madhu.sseUserEvent.controller;


import io.madhu.sseUserEvent.service.RandomUserEmitterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@Slf4j
@RequestMapping("/events")
public class RandomUserEmitterController {

    @Autowired
    private RandomUserEmitterService randomUserEmitterService;

    @GetMapping(value = "/subscribe/{eventType}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createEmitter(@PathVariable("eventType") String eventName) {
        return randomUserEmitterService.createEmitter(eventName);
    }
}
