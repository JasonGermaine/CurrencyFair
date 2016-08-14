package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.integration.db.DatabaseTradeProcessor;
import com.currencyfair.trading.integration.db.TradeEntity;
import com.currencyfair.trading.integration.db.TradeRepository;
import com.currencyfair.trading.integration.db.TradeStatisticCalculator;
import org.junit.Test;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author jasongermaine.
 */
public class DatabaseTradeProcessorTest {

    private final TradeRepository repository = mock(TradeRepository.class);
    private final TradeStatisticCalculator calculator = mock(TradeStatisticCalculator.class);
    private final Function<Trade, TradeEntity> tradeEntityFunction = trade -> new TradeEntity();
    private final Function<TradeEntity, Trade> entityTradeFunction = entity -> Trade.builder().build();
    private final DatabaseTradeProcessor processor
            = new DatabaseTradeProcessor(repository, tradeEntityFunction, entityTradeFunction, calculator);

    @Test
    public void testProcessTrade() throws Exception {
        final Trade tradeToPersist = Trade.builder().build();
        final TradeEntity entityFromDb = new TradeEntity();
        entityFromDb.setTradeId(1L);

        when(repository.save(any(TradeEntity.class))).thenReturn(entityFromDb);

        final Long tradeId =  processor.process(tradeToPersist);

        assertThat(tradeId).isEqualByComparingTo(entityFromDb.getTradeId());
    }
}