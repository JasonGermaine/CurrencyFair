package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.domain.model.TradeStatistic;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author jasongermaine.
 */
@Component
public class TradeStatisticCalculator {

    public TradeStatistic calculate(final List<Trade> trades) {
        final Map<String, Long> currencyPairFrequency = new HashMap<>();
        final Map<String, Long> originatingCountryFrequency = new HashMap<>();
        final Set<Long> userIds = new HashSet<>();
        trades.forEach(trade -> {
            userIds.add(trade.getUserId());
            currencyPairFrequency.compute(trade.getCurrencyPair().toString(), (key, val) -> val != null ? ++val : 1L);
            originatingCountryFrequency.compute(trade.getOriginatingCountry(), (key, val) -> val != null ? ++val : 1L);
        });
        final int tradesPlaced = trades.size();
        final int activeUsers = userIds.size();
        return TradeStatistic
                .builder()
                .tradesPlaced(tradesPlaced)
                .activeUsers(activeUsers)
                .currencyPairFrequency(currencyPairFrequency)
                .originatingCountryFrequency(originatingCountryFrequency)
                .build();
    }
}
