package com.currencyfair.trading.integration.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jasongermaine.
 */
@Repository
public interface TradeRepository extends JpaRepository<TradeEntity, Long> {

}
