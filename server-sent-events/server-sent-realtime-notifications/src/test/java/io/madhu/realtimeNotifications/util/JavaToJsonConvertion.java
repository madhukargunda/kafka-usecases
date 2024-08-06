/**
 * Author: Madhu
 * User:madhu
 * Date:7/7/24
 * Time:12:31 AM
 * Project: server-sent-realtime-notifications
 */

package io.madhu.realtimeNotifications.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.realtimeNotifications.model.EventDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


@Slf4j
public class JavaToJsonConvertion {

    @Test
    public void convertEventDtoToJson() {

        EventDto eventDto = new EventDto();
        eventDto.setEventId(123l);
        eventDto.setMemberId("madhukar");
        Map<String, Object> elements = new HashMap();
        eventDto.setType("USER_LOGIN_EVENT");
        elements.put("Key1", "Value1");
        elements.put("Key2", "Value2");
        elements.put("Key3", "Value3");
        eventDto.setData(elements);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(eventDto);
            log.info(" Print JSON  {} ",json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
