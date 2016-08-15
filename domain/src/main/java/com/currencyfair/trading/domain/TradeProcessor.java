package com.currencyfair.trading.domain;

import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.domain.model.TradeStatistic;

import java.time.ZonedDateTime;

/**
 * @author jasongermaine.
 */
public interface TradeProcessor {

    /**
     * Processes a trade and returns an associated ID for the given trade
     * @param trade - trade to be processed
     * @return trade id
     */
    Long process(final Trade trade);

    /**
     * Retrieves {@link TradeStatistic} for the current date
     * @return statistics
     */
    TradeStatistic retrieveProcessedTradeStatistics(final ZonedDateTime start, final ZonedDateTime end);
}
