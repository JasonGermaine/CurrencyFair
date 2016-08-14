package com.currencyfair.trading.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author jasongermaine.
 */
@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

    List<TradeEntity> findByExecutionTimeBetween(Timestamp start, Timestamp end);
}
