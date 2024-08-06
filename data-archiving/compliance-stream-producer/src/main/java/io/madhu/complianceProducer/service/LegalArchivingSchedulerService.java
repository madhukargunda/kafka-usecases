/**
 * Author: Madhu
 * User:madhu
 * Date:1/8/24
 * Time:6:56 AM
 * Project: compliance-stream-producer
 */

package io.madhu.complianceProducer.service;


import io.madhu.complianceProducer.constants.LegalArchivingProperties;
import io.madhu.complianceProducer.event.LegalArchivingEvent;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@EnableScheduling
public class LegalArchivingSchedulerService {

    private final static String TOPIC_NAME = "legal-archiving";

    private final ScheduledExecutorService scheduledExecutorService;
    private final LegalArchivingProperties legalArchivingProperties;
    private final KafkaTemplate<String, LegalArchivingEvent> kafkaTemplate;

    @Autowired
    public LegalArchivingSchedulerService(ScheduledExecutorService scheduledExecutorService, LegalArchivingProperties legalArchivingProperties, KafkaTemplate kafkaTemplate) {
        this.scheduledExecutorService = scheduledExecutorService;
        this.legalArchivingProperties = legalArchivingProperties;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostConstruct
    public void scheduleTasks() {
        log.info("LegalArchivingSchedulerService - postConstruct invoked");
        List<String> sites = legalArchivingProperties.getSupportedSites();
        long rate = legalArchivingProperties.getScheduleRate();
        log.info("The supported sites are {} ", sites);
        for (String site : sites) {
            scheduledExecutorService.scheduleAtFixedRate(() -> this.createAndExecuteTask(site), 0, 1, TimeUnit.MINUTES);
        }
    }

    public void createAndExecuteTask(String site) {
        log.info("Create and Execute Task method initiated for site {}..........", site);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusMinutes(legalArchivingProperties.getScheduleRate());
        LegalArchivingEvent legalArchivingEvent = new LegalArchivingEvent(site, startTime, endTime);
        //Blocking the code by thread
        CompletableFuture<SendResult<String, LegalArchivingEvent>> completableFuture = kafkaTemplate.send(TOPIC_NAME, legalArchivingEvent);
        completableFuture.whenComplete((result, exception) -> {
            if (Objects.isNull(exception)) {
                log.info("Sent message=[{}] to partition {} with offset=[{}]", legalArchivingEvent, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else
                log.error("Unable to send message=[{}] due to  exception: [{}]", legalArchivingEvent, exception.getMessage());
        });
//        completableFuture.thenAccept(result -> {
//                    log.info("Message sent successfully with offset: {}" + result.getRecordMetadata().offset());
//                })
//                .exceptionally(ex -> {
//                    log.error("Failed to send message: {}", ex.getMessage());
//                    return null;
//                });
    }
}
