/**
 * Author: Madhu
 * User:madhu
 * Date:7/7/24
 * Time:4:17 PM
 * Project: server-sent-events-examples
 */

package io.madhu.sseUserEvent.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.sseUserEvent.model.news.Article;
import io.madhu.sseUserEvent.model.news.News;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NewsArticlesClasspathService {

    @Value("classpath:news.json")
    private Resource resource;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Article> articleList;

    @SneakyThrows
    @PostConstruct
    public void NewsArticlesClasspathService() {
        log.info("File Path {}", resource.getFile().toPath());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        News news = objectMapper.readValue(Files.readString(resource.getFile().toPath()), News.class);
        this.articleList = news.getArticles();
    }

    @SneakyThrows
    public Flux<ServerSentEvent<List<Article>>> getNews() {
        log.info("Get News service method invoked");
        return Flux.fromIterable(this.articleList)
                .window(3)
                .delayElements(Duration.ofSeconds(2))
                .flatMap(Flux::collectList)
                .map(articleList -> ServerSentEvent.<List<Article>>builder().event("NEWS-EVENT").data(articleList).build());
    }

    public SseEmitter ssePublishNewsEvents() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        log.info("SSE Publishing Events to Client.........");
        SseEmitter sseEmitter = new SseEmitter();
        log.info("User Subscribed to EvenType {}");
        executorService.execute(() -> {
            try {
                while (true) {
                    //Window 3 elements
                    for (int i = 0; i < articleList.size(); i += 3) {
                        List<Article> responseData = articleList
                                .stream()
                                .skip(i)
                                .limit(3)
                                .collect(Collectors.toList());
                        TimeUnit.SECONDS.sleep(5);
                        sseEmitter.send(responseData, MediaType.APPLICATION_JSON);
                    }
                }
            } catch (Exception e) {
                sseEmitter.completeWithError(e);
            }
        });
        sseEmitter.onTimeout(() -> {
            executorService.shutdown();
            sseEmitter.complete();
        });
        sseEmitter.onCompletion(() -> executorService.shutdown());
        sseEmitter.onError((ex) -> {
            executorService.shutdown();
            sseEmitter.complete();
        });
        return sseEmitter;
    }
}
