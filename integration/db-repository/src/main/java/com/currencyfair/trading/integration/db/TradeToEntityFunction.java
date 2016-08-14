package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.model.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.function.Function;

/**
 * @author jasongermaine.
 */
@Component
@Slf4j
public class TradeToEntityFunction implements Function<Trade, TradeEntity> {

    @Override
    public TradeEntity apply(final Trade trade) {
        log.debug("Converting Trade to TradeEntity. Trade={}", trade);

        final TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setUserId(trade.getUserId());
        tradeEntity.setFxRate(trade.getFxRate());
        tradeEntity.setBid(trade.getBid().value());
        tradeEntity.setAsk(trade.getAsk().value());
        tradeEntity.setBaseCurrency(trade.getCurrencyPair().getBaseCurrency());
        tradeEntity.setCounterCurrency(trade.getCurrencyPair().getCounterCurrency());
        tradeEntity.setExecutionTime(Timestamp.from(trade.getExecutionTime().toInstant()));
        tradeEntity.setOriginatingCountry(trade.getOriginatingCountry());
        return tradeEntity;
    }
}
