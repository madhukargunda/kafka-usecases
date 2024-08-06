/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:3:16 PM
 * Project: wikimedia-event-stream-engine
 */


package io.madhu.wikimediaStreamEngine.handler;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import io.madhu.wikimediaStreamEngine.dispatcher.WikiMediaEventDispatcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WikimediaEventSourceHandler implements EventHandler {

    @Autowired
    private WikiMediaEventDispatcherService eventDispatcherService;

    @Override
    public void onOpen() throws Exception {
        log.info("Connection opened");
    }

    @Override
    public void onClosed() throws Exception {
        log.info("onClosed method");
    }

    @Override
    public void onMessage(String event, MessageEvent messageEvent) throws Exception {
        log.info("Event received: {} - Data: {}", event, messageEvent.getData());
        eventDispatcherService.dispatch(messageEvent.getData());
    }

    @Override
    public void onComment(String comments) throws Exception {
        log.info("Comment received: {}", comments);
    }

    @Override
    public void onError(Throwable t) {
        log.error("Error received {}", t);
    }
}
