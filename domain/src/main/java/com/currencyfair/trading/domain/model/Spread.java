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
        return new Spread(ask.value().subtract(bid.value()));
    }

    private final BigDecimal spread;

    private Spread(final BigDecimal spread) {
        this.spread = spread;
    }

    public BigDecimal value() {
        return spread;
    }
}
