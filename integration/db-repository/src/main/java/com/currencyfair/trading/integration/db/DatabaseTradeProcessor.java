package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.Trade;
import com.currencyfair.trading.domain.TradeProcessor;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jasongermaine.
 */

@Component
@ManagedResource(objectName = "TradingApp:name=DB")
public class DatabaseTradeProcessor implements TradeProcessor {

    private final TradeRepository tradeRepository;
    private final Function<Trade, TradeEntity> transform;

    @Autowired
    public DatabaseTradeProcessor(final TradeRepository tradeRepository,
                                  final Function<Trade, TradeEntity> transform) {
        this.tradeRepository = tradeRepository;
        this.transform = transform;
    }

    @Override
    public Long process(final Trade trade) {
        final TradeEntity entity = transform.apply(trade);
        return tradeRepository.save(entity).getTradeId();
    }


    @ManagedOperation(description = "Show data in DB.")
    public List<TradeEntity> displayTrades() {
        return tradeRepository.findAll();
    }
}
