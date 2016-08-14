package com.currencyfair.trading.integration.jms;

import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jasongermaine.
 */
@ToString
public class FxUpdate implements Serializable {
    private String currencyPair;
    private String lastUpdated;
    private String rateChange;
    private String buy;
    private String sell;
    private String spread;

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getRateChange() {
        return rateChange;
    }

    public void setRateChange(String rateChange) {
        this.rateChange = rateChange;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSpread() {
        return spread;
    }

    public void setSpread(String spread) {
        this.spread = spread;
    }
}
