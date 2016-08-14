package com.currencyfair.trading.intf;

import java.util.Map;

/**
 * @author jasongermaine.
 */
public class TradeStatisticDTO {
    private int tradesPlaced;
    private int activeUsers;
    private Map<String, Long> currencyPairFrequency;
    private Map<String, Long> originatingCountryFrequency;

    public int getTradesPlaced() {
        return tradesPlaced;
    }

    public void setTradesPlaced(int tradesPlaced) {
        this.tradesPlaced = tradesPlaced;
    }

    public int getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(int activeUsers) {
        this.activeUsers = activeUsers;
    }

    public Map<String, Long> getCurrencyPairFrequency() {
        return currencyPairFrequency;
    }

    public void setCurrencyPairFrequency(Map<String, Long> currencyPairFrequency) {
        this.currencyPairFrequency = currencyPairFrequency;
    }

    public Map<String, Long> getOriginatingCountryFrequency() {
        return originatingCountryFrequency;
    }

    public void setOriginatingCountryFrequency(Map<String, Long> originatingCountryFrequency) {
        this.originatingCountryFrequency = originatingCountryFrequency;
    }
}
