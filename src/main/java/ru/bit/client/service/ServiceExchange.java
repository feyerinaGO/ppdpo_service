package ru.bit.client.service;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ServiceExchange {
    private final CircuitBreaker circuitBreaker;
    private final WebClient loadBalancedClient;

    public ServiceExchange(ConfigurableApplicationContext ctx) {
        this.circuitBreaker = ctx.getBean(CircuitBreakerFactory.class).create("exchange");
        this.loadBalancedClient = ctx.getBean(WebClient.Builder.class).build();
    }

    public String exchange(String firstCurrancy, String secondCurrancy, int amount) {
        return circuitBreaker.run(() -> loadBalancedClient.get().uri("http://producers/convert/from/"
                                + firstCurrancy + "/to/" + secondCurrancy + "?value=" + amount)
                        .retrieve().toEntity(String.class).block().getBody(),
                throwable -> "Exchange server is currently unavailable");
    }
}
