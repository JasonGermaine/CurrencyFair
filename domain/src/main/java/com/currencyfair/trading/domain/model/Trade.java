package com.currencyfair.trading.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author jasongermaine.
 */
@Builder
@Value
public class Trade implements Serializable {
    long userId;
    CurrencyPair currencyPair;
    Bid bid;
    Ask ask;
    BigDecimal fxRate;
    ZonedDateTime executionTime;
    String originatingCountry;

    public Spread getSpread() {
        return Spread.calculate(bid, ask);
    }

    public boolean isMoreRecent(final Trade other) {
        return this.getExecutionTime().isAfter(other.getExecutionTime());
    }
}
