/**
 * Author: Madhu
 * User:madhu
 * Date:22/6/24
 * Time:4:48 PM
 * Project: random-user-stream-event
 */

package io.madhu.randomUser.service.impl;

import io.madhu.randomUser.model.User;
import io.madhu.randomUser.service.UserGenerationService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Profile("sse-polling")
@Slf4j
@EnableScheduling
public class UserGeneratorServiceImpl implements UserGenerationService<User> {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${randomUser.endpoint.url}")
    private String endPointUrl;

    @SneakyThrows
    @Override
    public User generateUser()  {
        return restTemplate.getForObject(new URI(endPointUrl), User.class);
    }
    /**
     * Polling every 5 seconds and get the User object
     * and send the event
     */
    @Scheduled(fixedRate = 5000)
    public void pollRandomUserEndpoint() {
        log.info("Polling generateUser method Triggered Duration of the 5 Seconds........... {}",this.generateUser());
    }
}
