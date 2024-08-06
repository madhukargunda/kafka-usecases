/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:12:58 PM
 * Project: wikimedia-event-stream-engine
 */

package io.madhu.wikimediaStreamEngine.service;


import com.launchdarkly.eventsource.EventSource;
import io.madhu.wikimediaStreamEngine.dispatcher.WikiMediaEventDispatcherService;
import io.madhu.wikimediaStreamEngine.handler.WikimediaEventSourceHandler;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Objects;

@Service
@Slf4j
public class LiveWikiMediaProducerService {

    @Autowired
    private WikiMediaEventDispatcherService wikiMediaEventDispatcherService;

    @Autowired
    private WikimediaEventSourceHandler eventSourceHandler;

    @Value("${spring.kafka.wikimedia.url}")
    private String wikimediaUrl;

    private EventSource eventSource;

    /**
     * okhttp-eventsource is a Java library that provides support for handling Server-Sent Events (SSE) using OkHttp,
     * a popular HTTP client for Java and Android.
     * It allows you to connect to an SSE endpoint, listen for events, and handle them in your Java applications.
     *
     * @return
     */
    public void startEventSource() {
        log.info("Realtime streaming data started ......");
        this.eventSource = new EventSource.Builder(eventSourceHandler, URI.create(wikimediaUrl)).build();
        eventSource.start();
    }

    @PreDestroy
    public void shutdown() {
        if (!Objects.isNull(this.eventSource))
            this.eventSource.close();
    }
}
