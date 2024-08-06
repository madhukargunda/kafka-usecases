/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:5:25 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.service;

import io.madhu.realtimeNotifications.mapper.EventMapper;
import io.madhu.realtimeNotifications.model.EventDto;
import io.madhu.realtimeNotifications.repository.EmitterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServerSentNotificationService implements NotificationService {

    private final EmitterRepository emitterRepository;
    private final EventMapper eventMapper;

    @Override
    @ResponseBody
    public void sendNotification(String memberId, EventDto eventDto) {
        log.info("Notification sent.....................................................");
        if (Objects.isNull(eventDto)) {
            log.info("Eventdto object received null , Please send the eventdto object data");
            return;
        }
        doSendNotification(memberId, eventDto);
    }

    private void doSendNotification(String memberId, EventDto eventDto) {
        emitterRepository.get(memberId).ifPresentOrElse(sseEmitter -> {
            log.info("Member %s present ...........");
            try {
                //ObjectMapper objectMapper = new ObjectMapper();
                //String eventDtoJson = objectMapper.writeValueAsString(eventDto);
                //sseEmitter.send(eventMapper.mapEventDtoToSseEmitter(eventDto),MediaType.APPLICATION_JSON);
                sseEmitter.send(eventDto, MediaType.APPLICATION_JSON);
//                ResponseBodyEmitter.DataWithMediaType dataWithMediaType =
//                        new ResponseBodyEmitter.DataWithMediaType(eventDto, MediaType.APPLICATION_JSON);
                // sseEmitter.send(dataWithMediaType);
                //Convert object to json
            } catch (IOException e) {
                log.info("Exception occurred while sending the Notification to Member {}", memberId);
                emitterRepository.remove(memberId);
            }
        }, () -> {
            log.info("Member Not found in the Repository");
        });
    }
}
