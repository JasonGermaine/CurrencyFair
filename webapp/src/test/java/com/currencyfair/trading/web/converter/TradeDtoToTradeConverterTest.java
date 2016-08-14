package com.currencyfair.trading.web.converter;

import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.intf.TradeDTO;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author jasongermaine.
 */
public class TradeDtoToTradeConverterTest {

    @Test
    public void testConvertValidObject() throws Exception {
        final int userId = 1;
        final BigDecimal amountBuy = new BigDecimal(10);
        final BigDecimal amountSell = new BigDecimal(9);
        final String originatingCountry = "IRE";
        final BigDecimal rate = new BigDecimal(1.3);
        final Date timePlaced = new Date();
        final String currencyFrom = "GBP";
        final String currencyTo = "USD";

        final TradeDTO dto = new TradeDTO();
        dto.setUserId(userId);
        dto.setCurrencyFrom(currencyFrom);
        dto.setCurrencyTo(currencyTo);
        dto.setRate(rate);
        dto.setAmountSell(amountSell);
        dto.setAmountBuy(amountBuy);
        dto.setOriginatingCountry(originatingCountry);
        dto.setTimePlaced(timePlaced);

        final Trade trade = new TradeDtoToTradeConverter().convert(dto);

        assertThat(trade.getUserId()).isEqualTo(userId);
        assertThat(trade.getAsk().value()).isEqualByComparingTo(amountBuy);
        assertThat(trade.getBid().value()).isEqualByComparingTo(amountSell);
        assertThat(trade.getCurrencyPair().getBaseCurrency()).isEqualTo(currencyFrom);
        assertThat(trade.getCurrencyPair().getCounterCurrency()).isEqualTo(currencyTo);
        assertThat(trade.getFxRate()).isEqualTo(rate);
        assertThat(trade.getOriginatingCountry()).isEqualTo(originatingCountry);
        assertThat(trade.getExecutionTime().toInstant()).isEqualByComparingTo(timePlaced.toInstant());
    }

    @Test
    public void testConvertWhenNull() throws Exception {
        final Trade trade = new TradeDtoToTradeConverter().convert(null);
        assertThat(trade).isNull();
    }
}