package com.currencyfair.trading.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

/**
 * @author jasongermaine.
 */
@Value(staticConstructor = "of")
@EqualsAndHashCode
@ToString
public class FxPair {
    final String from;
    final String to;
}
