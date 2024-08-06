package io.madhu.wikimediaConsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WikimediaEventStreamConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WikimediaEventStreamConsumerApplication.class, args);
    }
}
