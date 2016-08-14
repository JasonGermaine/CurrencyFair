package com.currencyfair.trading.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jasongermaine.
 */
@Value(staticConstructor = "of")
public class Bid implements Serializable {

    @Getter(value = AccessLevel.PRIVATE)
    BigDecimal bid;

    public BigDecimal value() {
        return bid;
    }
}
