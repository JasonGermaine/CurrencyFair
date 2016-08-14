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
        final String rateChange = cachedTrade == null
                ? "0"
                : toFormattedString(receivedTrade.getFxRate().subtract(cachedTrade.getFxRate()));
        final FxUpdate fxUpdate = new FxUpdate();
        fxUpdate.setCurrencyPair(receivedTrade.getCurrencyPair().toString());
        fxUpdate.setLastUpdated(formatter.format(receivedTrade.getExecutionTime()));
        fxUpdate.setRateChange(rateChange);
        fxUpdate.setBuy(toFormattedString(receivedTrade.getAsk().value()));
        fxUpdate.setSell(toFormattedString(receivedTrade.getBid().value()));
        fxUpdate.setSpread(toFormattedString(receivedTrade.getSpread().value()));
        return fxUpdate;
    }

    private String toFormattedString(final BigDecimal value) {
        return value.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
    }
}
