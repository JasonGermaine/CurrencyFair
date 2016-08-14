package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.model.CurrencyPair;
import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.domain.model.TradeStatistic;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author jasongermaine.
 */
public class TradeStatisticCalculatorTest {

    @Test
    public void testCalculate() throws Exception {
        final long userOne = 1L;
        final long userTwo = 2L;
        final CurrencyPair pairOne = CurrencyPair.of("EUR", "GBP");
        final CurrencyPair pairTwo = CurrencyPair.of("USD", "GBP");
        final String countryOne = "FR";
        final String countryTwo = "IRE";
        final Trade tradeOne = Trade.builder()
                .userId(userOne)
                .currencyPair(pairOne)
                .originatingCountry(countryOne)
                .build();
        final Trade tradeTwo = Trade.builder()
                .userId(userTwo)
                .currencyPair(pairOne)
                .originatingCountry(countryOne)
                .build();
        final Trade tradeThree = Trade.builder()
                .userId(userTwo)
                .currencyPair(pairTwo)
                .originatingCountry(countryTwo)
                .build();

        final List<Trade> trades = Arrays.asList(tradeOne, tradeTwo, tradeThree);
        final TradeStatistic stats = new TradeStatisticCalculator().calculate(trades);

        Assertions.assertThat(stats.getActiveUsers()).isEqualByComparingTo(2);
        Assertions.assertThat(stats.getTradesPlaced()).isEqualByComparingTo(3);

        Assertions.assertThat(stats.getCurrencyPairFrequency().size()).isEqualByComparingTo(2);
        Assertions.assertThat(stats.getCurrencyPairFrequency().get(pairOne.toString())).isEqualByComparingTo(2L);
        Assertions.assertThat(stats.getCurrencyPairFrequency().get(pairTwo.toString())).isEqualByComparingTo(1L);

        Assertions.assertThat(stats.getOriginatingCountryFrequency().size()).isEqualByComparingTo(2);
        Assertions.assertThat(stats.getOriginatingCountryFrequency().get(countryOne)).isEqualByComparingTo(2L);
        Assertions.assertThat(stats.getOriginatingCountryFrequency().get(countryTwo)).isEqualByComparingTo(1L);
    }
}