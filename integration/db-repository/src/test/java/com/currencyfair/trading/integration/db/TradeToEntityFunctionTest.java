package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.model.Ask;
import com.currencyfair.trading.domain.model.Bid;
import com.currencyfair.trading.domain.model.CurrencyPair;
import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.integration.db.TradeEntity;
import com.currencyfair.trading.integration.db.TradeToEntityFunction;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jasongermaine.
 */
public class TradeToEntityFunctionTest {

    @Test
    public void testApply() throws Exception {
        final int userId = 1;
        final BigDecimal bid = new BigDecimal(10);
        final BigDecimal ask = new BigDecimal(9);
        final String originatingCountry = "IRE";
        final BigDecimal fxRate = new BigDecimal(1.3);
        final ZonedDateTime executionTime = ZonedDateTime.now(ZoneId.of("UTC"));
        final String baseCurrency = "GBP";
        final String CounterCurrency = "USD";
        final Trade trade = Trade.builder()
                .userId(userId)
                .bid(Bid.of(bid))
                .ask(Ask.of(ask))
                .originatingCountry(originatingCountry)
                .fxRate(fxRate)
                .currencyPair(CurrencyPair.of(baseCurrency, CounterCurrency))
                .executionTime(executionTime)
                .build();

        final TradeEntity entity = new TradeToEntityFunction().apply(trade);

        assertThat(entity).isNotNull();
        assertThat(entity.getUserId()).isEqualTo(userId);
        assertThat(entity.getBid()).isEqualByComparingTo(bid);
        assertThat(entity.getAsk()).isEqualByComparingTo(ask);
        assertThat(entity.getFxRate()).isEqualTo(fxRate);
        assertThat(entity.getBaseCurrency()).isEqualTo(baseCurrency);
        assertThat(entity.getCounterCurrency()).isEqualTo(CounterCurrency);
        assertThat(entity.getOriginatingCountry()).isEqualTo(originatingCountry);
        assertThat(entity.getExecutionTime()).isEqualTo(Timestamp.from(executionTime.toInstant()));
    }
}