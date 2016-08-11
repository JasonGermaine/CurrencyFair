package com.currencyfair.trading.web.service;

import com.currencyfair.trading.domain.Trade;
import com.currencyfair.trading.domain.TradeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jasongermaine.
 */
@Service
@Slf4j
public class TradeService {

    private final TradeProcessor processor;

    @Autowired
    public TradeService(final TradeProcessor processor) {
        this.processor = processor;
    }

    public Long processTrade(final Trade trade) {

        return processor.process(trade);
    }
}
