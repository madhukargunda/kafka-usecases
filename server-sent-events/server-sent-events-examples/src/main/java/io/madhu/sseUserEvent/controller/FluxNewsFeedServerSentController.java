/**
 * Author: Madhu
 * User:madhu
 * Date:7/7/24
 * Time:3:19 PM
 * Project: server-sent-events-examples
 */

package io.madhu.sseUserEvent.controller;


import io.madhu.sseUserEvent.model.news.Article;
import io.madhu.sseUserEvent.service.NewsArticlesClasspathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FluxNewsFeedServerSentController {

    private final NewsArticlesClasspathService newsArticlesClasspathService;

    @GetMapping(value = "/news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<List<Article>>> getNews() {
        return newsArticlesClasspathService.getNews();
    }

    @GetMapping(value = "/sse-news", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createAndPublishEmitter(){
        return newsArticlesClasspathService.ssePublishNewsEvents();
    }
}
