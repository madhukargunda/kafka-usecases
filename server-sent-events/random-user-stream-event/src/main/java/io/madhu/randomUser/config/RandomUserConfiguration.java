/**
 * Author: Madhu
 * User:madhu
 * Date:23/6/24
 * Time:8:24 AM
 * Project: random-user-stream-event
 */

package io.madhu.randomUser.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class TemplateConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().build();
    }
}
