package io.madhu.wikimediaStreamEngine;

import io.madhu.wikimediaStreamEngine.service.LiveWikiMediaEventStreamService;
import io.madhu.wikimediaStreamEngine.service.LiveWikiMediaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Server-Sent Events (SSE) can be consumed using the EventSource Web API. In the example below,
 * Wikimedia SSE sends events via a REST endpoint, and the data is consumed using OkHttp EventSource.
 */

@SpringBootApplication
@Slf4j
public class WikimediaEventStreamEngineApplication implements CommandLineRunner {

    @Autowired
    private LiveWikiMediaProducerService liveWikiMediaProducerService;

    @Autowired
    private LiveWikiMediaEventStreamService liveWikiMediaEventStreamService;

    public static void main(String[] args) {
        SpringApplication.run(WikimediaEventStreamEngineApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Running WikimediaStreamEngine Application....");
        liveWikiMediaProducerService.startEventSource();
    }

    public void runLiveWikimediaReactiveService(){
        log.info("Calling the LiveWikiMediaReactiveService");
        liveWikiMediaEventStreamService.consumerWikimediaEvents().
                 doOnNext(event -> System.out.println("Received event: " + event)).
                 blockLast(); //Logic to add send the events to Kafka
    }
}
