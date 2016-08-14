package com.currencyfair.trading.integration.db;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author jasongermaine.
 */
@Data
@Entity(name = "TRADE")
public class TradeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long tradeId;

    @Column
    private Long userId;

    @Column
    private String baseCurrency;

    @Column
    private String counterCurrency;

    @Column
    private BigDecimal ask;

    @Column
    private BigDecimal bid;

    @Column
    private BigDecimal fxRate;

    @Column
    private String originatingCountry;

    @Column
    private Timestamp executionTime;
}
