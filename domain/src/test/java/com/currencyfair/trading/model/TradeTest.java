package com.currencyfair.trading.model;

import com.currencyfair.trading.domain.model.Ask;
import com.currencyfair.trading.domain.model.Bid;
import com.currencyfair.trading.domain.model.Trade;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author jasongermaine.
 */
public class TradeTest {

    @Test
    public void testGetSpread() throws Exception {
        final BigDecimal bid = new BigDecimal("10.1234");
        final BigDecimal ask = new BigDecimal("10.1027");

        final Trade trade = Trade.builder()
                .bid(Bid.of(bid))
                .ask(Ask.of(ask))
                .build();

        assertEquals(ask.subtract(bid), trade.getSpread().value());
    }
}