package com.currencyfair.trading.web.service;

import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.domain.TradeProcessor;
import com.currencyfair.trading.domain.model.TradeStatistic;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;

import java.time.ZonedDateTime;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author jasongermaine.
 */
public class TradeServiceTest {

    private final TradeProcessor processor = mock(TradeProcessor.class);
    private final JmsTemplate jmsTemplate = mock(JmsTemplate.class);
    private final TradeService service = new TradeService(processor, jmsTemplate, "queue");

    @Test
    public void testProcessTrade() throws Exception {
        long expectedId = 1L;
        when(processor.process(any())).thenReturn(expectedId);

        final Long actualId = service.processTrade(Trade.builder().build());

        Assertions.assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    public void testRetrieveTradeStatistics() throws Exception {
        final TradeStatistic expected = TradeStatistic.builder().build();
        when(processor.retrieveProcessedTradeStatistics(any(), any())).thenReturn(expected);

        final TradeStatistic tradeStatistic = service.retrieveTradeStatistics();

        Assertions.assertThat(tradeStatistic).isEqualTo(expected);
    }
}