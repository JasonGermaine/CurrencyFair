package com.currencyfair.trading.domain;

/**
 * @author jasongermaine.
 */
public interface TradeProcessor {

    Long process(final Trade trade);
}
