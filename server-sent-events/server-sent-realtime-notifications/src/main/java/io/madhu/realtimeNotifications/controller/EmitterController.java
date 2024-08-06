/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:4:20 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.controller;

import io.madhu.realtimeNotifications.model.EventDto;
import io.madhu.realtimeNotifications.service.EmitterService;
import io.madhu.realtimeNotifications.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EmitterController {

    private final EmitterService emitterService;
    private final NotificationService notificationService;

    @GetMapping("/{memberId}")
    @ResponseBody
    public SseEmitter subscribe(@PathVariable String memberId) {
        log.info(String.format("Member with id %s Subscribed....",memberId));
        return emitterService.createEmitter(memberId);
    }

    @PostMapping("/{memberId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void publishEvent(@PathVariable String memberId, @RequestBody EventDto eventDto){
        log.info("Publishing the Event to memberId {} ",eventDto);
        notificationService.sendNotification(memberId,eventDto);
    }
}
