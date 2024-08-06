/**
 * Author: Madhu
 * User:madhu
 * Date:24/7/24
 * Time:11:06 PM
 * Project: sse-sending-file-content
 */

package io.madhu.sendingFileContent.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.sendingFileContent.listener.FileListener;
import io.madhu.sendingFileContent.model.FileContentEvent;
import io.madhu.sendingFileContent.service.FilePollingService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@Slf4j
public class FileSseController implements FileListener {

    private CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private FilePollingService filePollingService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        filePollingService.registerListener(this);
    }

    @GetMapping("/file-stream")
    public SseEmitter streamFiles() {
        SseEmitter emitter = new SseEmitter();
        emitters.add(emitter);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        return emitter;
    }

    @Override
    public void onFileDetected(String content) {
        for (SseEmitter emitter : emitters) {
            try {
                String jsonContent = objectMapper.writeValueAsString(new FileContentEvent(this,content));
                emitter.send(SseEmitter.event().data(jsonContent).name("file-content"));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }

    @EventListener
    public void handleFileContentEvent(FileContentEvent event) {
        String content = event.getContent();
        for (SseEmitter emitter : emitters) {
            try {
                String jsonContent = objectMapper.writeValueAsString(new FileContentEvent(this,content));
                emitter.send(SseEmitter.event().data(jsonContent).name("file-content"));
            } catch (IOException e) {
                emitters.remove(emitter);
            }
        }
    }
}
