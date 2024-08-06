/**
 * Author: Madhu
 * User:madhu
 * Date:23/7/24
 * Time:11:51 AM
 * Project: sse-stock-quotes
 */

package io.madhu.sSEstockQuotes.publish;

import io.madhu.sSEstockQuotes.model.Stock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class StockPriceChanger {

    private final ApplicationEventPublisher publisher;

    private final Random random = new Random();

    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

    public StockPriceChanger(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
        this.executor.schedule(this::changePrice, 1, TimeUnit.SECONDS);
    }

    //Recursively calling the same function
    private void changePrice() {
        log.info("changePrice method invoked......{}");
        BigDecimal price = BigDecimal.valueOf(random.nextGaussian());
        publisher.publishEvent(new Stock(price));
        executor.schedule(() -> this.changePrice(), random.nextInt(10000), TimeUnit.MILLISECONDS);
    }
}
