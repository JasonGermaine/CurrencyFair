package com.currencyfair.trading.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author jasongermaine.
 */
@Builder
@Value
@EqualsAndHashCode
@ToString
public class Trade {
    final long userId;
    final FxPair fxPair;
    final BigDecimal buyPrice;
    final BigDecimal sellPrice;
    final BigDecimal fxRate;
    final ZonedDateTime executionTime;
    final String originatingCountry;
}
