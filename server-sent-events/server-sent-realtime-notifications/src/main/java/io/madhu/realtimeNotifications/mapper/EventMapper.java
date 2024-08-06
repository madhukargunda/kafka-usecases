/**
 * Author: Madhu
 * User:madhu
 * Date:6/7/24
 * Time:5:38 PM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.mapper;

import io.madhu.realtimeNotifications.model.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@Slf4j
public class EventMapper {

    public SseEmitter.SseEventBuilder mapEventDtoToSseEmitter(EventDto eventDto) {
        log.info("Creating the SseEmitter SseEventBuilder object..........");
        return SseEmitter.event()
                .name(eventDto.getType())
                .data(eventDto.getData())
                .id(eventDto.getMemberId())
                .comment(String.format("MemberID : %s", eventDto.getMemberId()));
    }
}
