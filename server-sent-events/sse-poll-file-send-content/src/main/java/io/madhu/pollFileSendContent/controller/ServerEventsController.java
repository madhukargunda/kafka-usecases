/**
 * Author: Madhu
 * User:madhu
 * Date:26/7/24
 * Time:12:17 PM
 * Project: sse-poll-file-send-content
 */

package io.madhu.pollFileSendContent.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@RestController
@Slf4j
@RequestMapping("/events")
public class ServerEventsController {

    @Value("classpath:Sample-Spreadsheet-100-rows.csv")
    Resource resourceFile;

    private AtomicInteger atomicInteger = new AtomicInteger();

    @GetMapping(path = "file-events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> subscribeAndSend() throws IOException {
        log.info("Get events method triggered.... {}",resourceFile.getFile().toPath());
        Stream<String> lines = Files.lines(resourceFile.getFile().toPath(), StandardCharsets.UTF_16);
       // lines.forEach(t -> log.info(t));
        return Flux.<String>fromStream(lines)
                .filter(line -> !line.isEmpty())
                .map(line -> ServerSentEvent.<String>builder().event("FILE-SEND-EVENT")
                        .data(line).retry(Duration.ofSeconds(2))
                        .id(String.valueOf(atomicInteger.getAndIncrement()))
                        .build())
                .delayElements(Duration.ofSeconds(10));
    }
}
