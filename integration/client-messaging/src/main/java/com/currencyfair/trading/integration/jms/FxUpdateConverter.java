package com.currencyfair.trading.integration.jms;

import com.currencyfair.trading.domain.model.Trade;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

/**
 * @author jasongermaine.
 */
@Component
public class FxUpdateConverter {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

    public FxUpdate convert(final Trade trade) {
        return convert(trade, null);
    }

    public FxUpdate convert(final Trade receivedTrade, final Trade cachedTrade) {
        final BigDecimal rateChange = cachedTrade == null ? BigDecimal.ZERO : receivedTrade.getFxRate().subtract(cachedTrade.getFxRate()).setScale(4, BigDecimal.ROUND_HALF_UP);
        final FxUpdate fxUpdate = new FxUpdate();
        final BigDecimal spread = receivedTrade.getSpread().value().setScale(4, BigDecimal.ROUND_HALF_UP);
        fxUpdate.setCurrencyPair(receivedTrade.getCurrencyPair().toString());
        fxUpdate.setLastUpdated(formatter.format(receivedTrade.getExecutionTime()));
        fxUpdate.setRateChange(rateChange);
        fxUpdate.setBuy(receivedTrade.getAsk().value().setScale(4, BigDecimal.ROUND_HALF_UP));
        fxUpdate.setSell(receivedTrade.getBid().value().setScale(4, BigDecimal.ROUND_HALF_UP));
        fxUpdate.setSpread(spread);
        return fxUpdate;
    }
}
