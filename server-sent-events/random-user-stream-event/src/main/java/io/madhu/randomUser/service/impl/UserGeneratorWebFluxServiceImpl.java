/**
 * Author: Madhu
 * User:madhu
 * Date:22/6/24
 * Time:5:27 PM
 * Project: random-user-stream-event
 */

package io.madhu.randomUser.service.impl;

import io.madhu.randomUser.model.User;
import io.madhu.randomUser.service.UserGenerationService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
@Slf4j
@Profile("sse-flux")
@EnableScheduling
public class UserGeneratorWebFluxServiceImpl implements UserGenerationService<Flux<User>> {

    private final WebClient webClient;

    public UserGeneratorWebFluxServiceImpl(@Value("${randomUser.endpoint.url}") String endPointUrl, WebClient.Builder webClientBuilder) {
        log.info("The Endpoint URL : {}", endPointUrl);
        this.webClient = webClientBuilder.baseUrl(endPointUrl).build();
    }

    /**
     * This method Emits the data to subscriber
     * Emit: The action of producing and sending a new item,
     * signal, or error to the subscribers.
     * FLux Represents the stream of data
     * Mono Represent the single value.
     * <p>
     * A sequence of events (data, signals, or errors) that are asynchronously pushed to subscribers.
     *
     * @return
     */
    @Override
    @Scheduled(fixedDelay = 5000)
    public Flux<User> generateUser() {
        log.info("Reactive Way of Generating the Random user ........");
        return Flux.interval(Duration.ofSeconds(10))
                .flatMap(tick -> webClient.get().retrieve().bodyToMono(User.class));
    }

}
