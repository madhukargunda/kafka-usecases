package io.madhu.randomUser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Slf4j
public class RandomUserStreamEventApplication {

	public static void main(String[] args) {
		log.info("RandomUserStreamEventApplication service initiated......................");
		SpringApplication.run(RandomUserStreamEventApplication.class, args);
	}
}
