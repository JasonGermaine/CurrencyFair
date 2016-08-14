package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.integration.db.EntityToTradeFunction;
import com.currencyfair.trading.integration.db.TradeEntity;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jasongermaine.
 */
public class EntityToTradeFunctionTest {

    @Test
    public void testApply() throws Exception {
        final long userId = 1L;
        final BigDecimal bid = new BigDecimal(10);
        final BigDecimal ask = new BigDecimal(9);
        final String originatingCountry = "IRE";
        final BigDecimal fxRate = new BigDecimal(1.3);
        final ZonedDateTime executionTime = ZonedDateTime.now(ZoneId.of("UTC"));
        final String baseCurrency = "GBP";
        final String counterCurrency = "USD";

        final TradeEntity tradeEntity = new TradeEntity();
        tradeEntity.setUserId(userId);
        tradeEntity.setBid(bid);
        tradeEntity.setAsk(ask);
        tradeEntity.setOriginatingCountry(originatingCountry);
        tradeEntity.setFxRate(fxRate);
        tradeEntity.setExecutionTime(Timestamp.from(executionTime.toInstant()));
        tradeEntity.setBaseCurrency(baseCurrency);
        tradeEntity.setCounterCurrency(counterCurrency);

        final Trade trade = new EntityToTradeFunction().apply(tradeEntity);

        assertThat(trade.getUserId()).isEqualTo(userId);
        assertThat(trade.getBid().value()).isEqualByComparingTo(bid);
        assertThat(trade.getAsk().value()).isEqualByComparingTo(ask);
        assertThat(trade.getFxRate()).isEqualTo(fxRate);
        assertThat(trade.getCurrencyPair().getBaseCurrency()).isEqualTo(baseCurrency);
        assertThat(trade.getCurrencyPair().getCounterCurrency()).isEqualTo(counterCurrency);
        assertThat(trade.getOriginatingCountry()).isEqualTo(originatingCountry);
        assertThat(trade.getExecutionTime()).isEqualTo(executionTime);
    }
}