package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.Trade;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @author jasongermaine.
 */
@Component
public class TradeToEntityFunction implements Function<Trade, TradeEntity> {

    @Override
    public TradeEntity apply(final Trade trade) {
        final TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setUserId(trade.getUserId());
        tradeEntity.setRate(trade.getFxRate());
        tradeEntity.setAmountBuy(trade.getBuyPrice());
        tradeEntity.setAmountSell(trade.getSellPrice());
        tradeEntity.setCurrencyFrom(trade.getFxPair().getFrom());
        tradeEntity.setCurrencyTo(trade.getFxPair().getTo());
        tradeEntity.setTimePlaced(Timestamp.from(trade.getExecutionTime().toInstant()));
        tradeEntity.setOriginatingCountry(trade.getOriginatingCountry());
        return tradeEntity;
    }
}
