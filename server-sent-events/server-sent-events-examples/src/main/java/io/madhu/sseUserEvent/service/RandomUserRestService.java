/**
 * Author: Madhu
 * User:madhu
 * Date:7/7/24
 * Time:2:54 PM
 * Project: server-sent-events-examples
 */

package io.madhu.sseUserEvent.service;

import io.madhu.sseUserEvent.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
@Slf4j
public class RandomUserRestService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${spring.random.user.url}")
    private String randomUserEndpoint;

    public User getRandomUser(){
        return this.getUser();
    }

    /**
     * Fetch RandomUsers using the RestTemplate
     *
     * @return
     */
    private User getUser() {
        log.info("RandomUser Rest API is triggered......................");
        return restTemplate.getForObject(URI.create(randomUserEndpoint), User.class);
    }
}
