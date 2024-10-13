/**
 * Author: Madhu
 * User:madhu
 * Date:8/9/24
 * Time:11:36 AM
 * Project: library-events-producer
 */

package io.madhu.libraryEventsProducer.service;

import io.madhu.libraryEventsProducer.events.LibraryEvent;
import io.madhu.libraryEventsProducer.producer.LibraryEventProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LibraryEventService {

    @Autowired
    private LibraryEventProducerService libraryEventProducerService;


    /**
     * 1.Blocks the call and get the cluster metadata
     * 2.Then sends the data to kafka topic
     * @param libraryEvent
     */
    public void create(LibraryEvent libraryEvent) {
        log.info("Processing the Library events");
         libraryEventProducerService.send(libraryEvent);
    }
}
