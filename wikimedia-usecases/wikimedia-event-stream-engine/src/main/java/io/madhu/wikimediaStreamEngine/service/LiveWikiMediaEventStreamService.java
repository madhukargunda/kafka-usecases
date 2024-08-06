/**
 * Author: Madhu
 * User:madhu
 * Date:9/7/24
 * Time:5:12 PM
 * Project: wikimedia-event-stream-engine
 */

package io.madhu.wikimediaStreamEngine.service;


import io.madhu.wikimediaStreamEngine.dispatcher.WikiMediaEventDispatcherService;
import io.madhu.wikimediaStreamEngine.model.WikiMedia;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;

@Service
@Profile("wikimedia-stream")
@Slf4j
public class LiveWikiMediaEventStreamService {

    private final WebClient webClient;

    private final WikiMediaEventDispatcherService eventDispatcherService;

    @Value("${spring.wikimedia.url}")
    private String wikimediaUrl;

    public LiveWikiMediaEventStreamService(WebClient.Builder webclientBuilder, WikiMediaEventDispatcherService eventDispatcherService) {
        this.webClient = webclientBuilder.baseUrl(wikimediaUrl).build();
        this.eventDispatcherService = eventDispatcherService;
    }

    public Flux<WikiMedia> consumerWikimediaEvents() {
        Flux<WikiMedia> wikiMediaFlux = webClient.get().retrieve().bodyToFlux(WikiMedia.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    System.err.println("Error consuming SSE: " + e.getMessage());
                    return Flux.empty();
                });
        ;
        wikiMediaFlux.doOnNext((d) -> log.info("Wikimedia event data {}", d));
        return wikiMediaFlux;
    }
}
