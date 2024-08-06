/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:1:22 PM
 * Project: wikimedia-event-stream-engine
 */

package io.madhu.wikimediaStreamEngine.scheduler;

import io.madhu.wikimediaStreamEngine.service.LiveWikiMediaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@Profile("polling")
@Slf4j
public class ScheduledTask {

    LiveWikiMediaProducerService liveWikiMediaProducerService;

    @Scheduled(fixedDelay = 5000)
    public void emitWikimediaEvents(){
        log.info("This emitWikimediaEvents NOT IMPLEMENTED now");
    }
}
