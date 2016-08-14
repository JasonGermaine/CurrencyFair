package com.currencyfair.trading.domain.model;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jasongermaine.
 */
@Builder
@Value
public class TradeStatistic implements Serializable {

    int tradesPlaced;
    int activeUsers;
    Map<String, Long> currencyPairFrequency;
    Map<String, Long> originatingCountryFrequency;
}
