/**
 * Author: Madhu
 * User:madhu
 * Date:23/7/24
 * Time:3:03 PM
 * Project: sse-stock-quotes
 */

package io.madhu.sSEstockQuotes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.sSEstockQuotes.model.Stock;
import io.madhu.sSEstockQuotes.repository.SetBasedEmitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class StockController {

    @Autowired
    private SetBasedEmitterRepository repository;

    @GetMapping("/stocks-stream") //2.2
    public SseEmitter subscribe() {
        SseEmitter sseEmitter = new SseEmitter();
        repository.addOrUpdate(sseEmitter);
        sseEmitter.onTimeout(() -> repository.remove(sseEmitter));
        sseEmitter.onError(throwable -> repository.remove(sseEmitter));
        sseEmitter.onError((e) -> {
            repository.remove(sseEmitter);
        });
        return sseEmitter;
    }

    @Async
    @EventListener
    public void stockMessageHandler(Stock stock) {
        log.info("Stock Event Listened ...");
        List<SseEmitter> errorEmitters = new ArrayList<>();
        repository.getAll().forEach(emitter -> {
            try {
                emitter.send(emitter.event().data(new ObjectMapper().writeValueAsBytes(stock))
                        .name("file-content")
                        .reconnectTime(3000)); //MediaType.APPLICATION_JSON);
            } catch (Exception e) {
                errorEmitters.add(emitter);
            }
        });
        errorEmitters.forEach(repository::remove);
    }
}
