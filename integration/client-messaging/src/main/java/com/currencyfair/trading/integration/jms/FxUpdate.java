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
    private BigDecimal rateChange;
    private BigDecimal buy;
    private BigDecimal sell;
    private BigDecimal spread;

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

    public BigDecimal getRateChange() {
        return rateChange;
    }

    public void setRateChange(BigDecimal rateChange) {
        this.rateChange = rateChange;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSell() {
        return sell;
    }

    public void setSell(BigDecimal sell) {
        this.sell = sell;
    }

    public BigDecimal getSpread() {
        return spread;
    }

    public void setSpread(BigDecimal spread) {
        this.spread = spread;
    }
}
