package com.currencyfair.trading.domain.model;

import lombok.Value;

import java.io.Serializable;

/**
 * @author jasongermaine.
 */
@Value(staticConstructor = "of")
public class CurrencyPair implements Serializable {
    String baseCurrency;
    String counterCurrency;

    @Override
    public String toString() {
        return baseCurrency + "/" + counterCurrency;
    }
}
