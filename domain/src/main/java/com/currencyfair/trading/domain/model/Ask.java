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
public class Ask implements Serializable {

    @Getter(value = AccessLevel.PRIVATE)
    BigDecimal ask;

    public BigDecimal value() {
        return ask;
    }
}
