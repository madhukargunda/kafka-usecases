/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:1:16 PM
 * Project: wikimedia-event-stream-engine
 */

package io.madhu.wikimediaStreamEngine.dispatcher;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WikiMediaEventDispatcherService {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    NewTopic newTopic;

    /**
     * Dispatching the wikimedia event to kafka
     *
     * @param wikimediaEvent
     */
    public void dispatch(String wikimediaEvent) {
        log.debug("Wikimedia Event Dispatched to Topic");
        // kafkaTemplate.send(newTopic.name(), wikimediaEvent);
    }
}
