package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.model.Ask;
import com.currencyfair.trading.domain.model.Bid;
import com.currencyfair.trading.domain.model.CurrencyPair;
import com.currencyfair.trading.domain.model.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Function;

/**
 * @author jasongermaine.
 */
@Component
@Slf4j
public class EntityToTradeFunction implements Function<TradeEntity, Trade> {

    @Override
    public Trade apply(final TradeEntity entity) {
        log.debug("Converting TradeEntity to Trade. TradeEntity={}", entity);
        final ZoneId zoneId = ZoneId.of("UTC");
        return Trade.builder()
                .userId(entity.getUserId())
                .fxRate(entity.getFxRate())
                .bid(Bid.of(entity.getBid()))
                .ask(Ask.of(entity.getAsk()))
                .currencyPair(CurrencyPair.of(entity.getBaseCurrency(), entity.getCounterCurrency()))
                .originatingCountry(entity.getOriginatingCountry())
                .executionTime(LocalDateTime.ofInstant(entity.getExecutionTime().toInstant(), zoneId).atZone(zoneId))
                .build();
    }
}
