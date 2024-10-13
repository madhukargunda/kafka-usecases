package io.madhu.kafkaConcurrentFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * In kafka consumer group is designed to consume one message from one partition .
 *  with in the same consumer can process the multiple data items from partition at the same time
 *
 * how to implement consumer can process message concurrently after fetched
 */
@SpringBootApplication
public class ParallelKafkaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParallelKafkaConsumerApplication.class, args);
	}

}
