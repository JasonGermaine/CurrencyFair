package com.currencyfair.trading.domain.model;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jasongermaine.
 */
@EqualsAndHashCode
@ToString
public class Spread implements Serializable {

    public static Spread calculate(final Bid bid, final Ask ask) {
        // % Spread = 100 × (Ask Price - Bid Price) / Ask Price
        final BigDecimal askValue = ask.value();
        final BigDecimal diff = askValue.subtract(bid.value());
        final BigDecimal spreadValue =
                new BigDecimal(100).multiply(
                        diff.divide(askValue, 4, BigDecimal.ROUND_HALF_UP));
        return new Spread(spreadValue);
    }

    private final BigDecimal spread;

    private Spread(final BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal value() {
        return spread;
    }
}
