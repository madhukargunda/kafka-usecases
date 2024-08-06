/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:7:43 PM
 * Project: wikimedia-event-stream-consumer
 */

package io.madhu.wikimediaConsumer.listener;


import io.madhu.wikimediaConsumer.entity.WikiMediaData;
import io.madhu.wikimediaConsumer.repository.WikiMediaDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WikiMediaEventListener {

    @Autowired
    private WikiMediaDataRepository wikiMediaDataRepository;

    @KafkaListener(topics = "wiki-media", groupId = "wikimedia-group")
    public void listenWikiMediaEvents(String wikiMediaMessage) {
        log.info(String.format("Event Message Received --> %s", wikiMediaMessage));
        WikiMediaData wikiMediaData = new WikiMediaData();
        wikiMediaData.setEventData(wikiMediaMessage);
        wikiMediaDataRepository.save(wikiMediaData);
    }
}
