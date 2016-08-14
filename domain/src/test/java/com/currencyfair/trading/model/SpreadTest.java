package com.currencyfair.trading.model;

import com.currencyfair.trading.domain.model.Ask;
import com.currencyfair.trading.domain.model.Bid;
import com.currencyfair.trading.domain.model.Spread;
import com.currencyfair.trading.domain.model.Trade;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * @author jasongermaine.
 */
public class SpreadTest {

    @Test
    public void testCalculateSpread() throws Exception {
        final BigDecimal bid = new BigDecimal("1000");
        final BigDecimal ask = new BigDecimal("1200");

        final Spread spread = Spread.calculate(Bid.of(bid), Ask.of(ask));

        /*
            % Spread = 100 × (Ask Price - Bid Price) / Ask Price

            (1200 - 1000) / 1200 = 0.1667
            100 * 0.1667 = 16.6700
         */
        final BigDecimal expected = new BigDecimal(16.6700).setScale(4, BigDecimal.ROUND_HALF_UP);
        assertEquals(expected, spread.value());
    }
}