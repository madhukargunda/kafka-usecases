/**
 * Author: Madhu
 * User:madhu
 * Date:8/9/24
 * Time:11:33 AM
 * Project: library-events-producer
 */

package io.madhu.libraryEventsProducer.controller;


import io.madhu.libraryEventsProducer.constants.LibraryEventType;
import io.madhu.libraryEventsProducer.events.LibraryEvent;
import io.madhu.libraryEventsProducer.service.LibraryEventService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class LibraryEventsController {

    @Autowired
    LibraryEventService libraryEventService;

    @PostMapping("/v1/libraryEvents")
    public ResponseEntity<?> processLibraryEvents(@RequestBody @Valid LibraryEvent libraryEvent) {
        log.info("Received LibraryEvent {} -->", libraryEvent);
        libraryEventService.create(libraryEvent);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).build();
    }

    @PutMapping("/v1/libraryEvents")
    public ResponseEntity<?> updateLibraryEvent(@RequestBody @Valid LibraryEvent libraryEvent) {
        log.info("Updating the library event ");
        if (Objects.nonNull(libraryEvent.getLibraryEventId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the LibraryEventId");
        }
        if(!libraryEvent.getLibraryEventType().equals(LibraryEventType.UPDATE)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only supports UPDATE event ");
        }
        libraryEventService.create(libraryEvent);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
