package com.currencyfair.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * @author jasongermaine.
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableJms
@EnableWebSocketMessageBroker
public class TradingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingApplication.class, args);
    }

}
