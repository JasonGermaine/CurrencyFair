package com.currencyfair.trading.integration.db;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author jasongermaine.
 */
@Data
@Entity
public class TradeEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long tradeId;
    Long userId;
    String currencyFrom;
    String currencyTo;
    BigDecimal amountSell;
    BigDecimal amountBuy;
    BigDecimal rate;
    String originatingCountry;
    Timestamp timePlaced;
}
