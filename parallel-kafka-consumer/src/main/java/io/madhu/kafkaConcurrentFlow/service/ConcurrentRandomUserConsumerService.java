/**
 * Author: Madhu
 * User:madhu
 * Date:3/9/24
 * Time:11:25 AM
 * Project: parallel-kafka-consumer
 */

package io.madhu.kafkaConcurrentFlow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.madhu.kafkaConcurrentFlow.bo.User;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutorService;

@Service
@AllArgsConstructor
@EnableKafka
@Slf4j
public class ConcurrentRandomUserConsumerService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private KafkaConsumer<String, String> kafkaConsumer;

    @Autowired
    private KafkaProperties kafkaProperties;

    @Autowired
    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        log.info("Write any logic if required");
        //consumeAndProcess();
    }

    public void consumeAndProcess() {
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(1000));
            //parallelStreams
            // records.records(new TopicPartition()).parallelStream().forEach(this::processRecord);
            //Fetch Messages in Batches: Use the poll() method to fetch a batch of messages.
            for (ConsumerRecord<String, String> record : records) {
                //For each message in the fetched batch, assign it to a thread for processing.
                //we can use a thread pool to manage the threads
                executorService.submit(() -> processRecord(record));
            }
            kafkaConsumer.commitAsync();
        }
    }

    private User processRecord(ConsumerRecord<String, String> record) {
        // Process the record
        log.info(String.format("Processing record with key %s and value %s%n", record.key(), record.value()));
        try {
            return objectMapper.readValue(record.value(), User.class);
        } catch (JsonProcessingException e) {
            log.info("Exception occured {}", e);
            throw new RuntimeException(e);
        }
    }
}
