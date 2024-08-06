/**
 * Author: Madhu
 * User:madhu
 * Date:27/6/24
 * Time:2:10 AM
 * Project: sse-user-events
 */

package io.madhu.sseUserEvent.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
