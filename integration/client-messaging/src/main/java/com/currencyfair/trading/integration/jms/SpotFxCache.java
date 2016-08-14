package com.currencyfair.trading.integration.jms;

import com.currencyfair.trading.domain.model.Ask;
import com.currencyfair.trading.domain.model.Bid;
import com.currencyfair.trading.domain.model.CurrencyPair;
import com.currencyfair.trading.domain.model.Trade;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jasongermaine.
 */
@Component
public class SpotFxCache {

    private final Map<CurrencyPair, Trade> latestSpotFx = new ConcurrentHashMap<>();

    public Trade get(CurrencyPair key) {
        return latestSpotFx.get(key);
    }

    public Trade put(CurrencyPair key, Trade value) {
        return latestSpotFx.put(key, value);
    }

    public Collection<Trade> values() {
        return latestSpotFx.values();
    }

    @PostConstruct
    private void init() {
        final CurrencyPair pair = CurrencyPair.of("EUR", "GBP");
        final Trade trade = Trade.builder()
                .currencyPair(pair)
                .bid(Bid.of(new BigDecimal(0.8634)))
                .ask(Ask.of(new BigDecimal(0.8644)))
                .fxRate(new BigDecimal(0.86339))
                .originatingCountry("IRE")
                .executionTime(ZonedDateTime.of(LocalDate.now(), LocalTime.now().minusHours(3), ZoneId.of("UTC")))
                .userId(0)
                .build();
        put(pair, trade);
    }
}
