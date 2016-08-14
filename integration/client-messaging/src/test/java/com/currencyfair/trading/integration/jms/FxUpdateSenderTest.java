package com.currencyfair.trading.integration.jms;

import com.currencyfair.trading.domain.model.CurrencyPair;
import com.currencyfair.trading.domain.model.Trade;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author jasongermaine.
 */
public class FxUpdateSenderTest {

    private static final String DESTINATION = "/fx/update";

    private final SimpMessagingTemplate frontEndSocket = mock(SimpMessagingTemplate.class);
    private final SpotFxCache cache = mock(SpotFxCache.class);
    private final FxUpdateConverter converter = mock(FxUpdateConverter.class);
    private final FxUpdateSender sender = new FxUpdateSender(frontEndSocket, cache, converter);

    @Before
    public void setUp() throws Exception {
        reset(frontEndSocket, cache, converter);
    }

    @Test
    public void testNoUpdateSentWhenCachedTradeIsMoreRecent() {
        final CurrencyPair currencyPair = CurrencyPair.of("EUR", "GBP");
        final ZonedDateTime laterDate = ZonedDateTime.of(LocalDateTime.MAX, ZoneId.systemDefault());
        final ZonedDateTime earlierDate = ZonedDateTime.of(LocalDateTime.MIN, ZoneId.systemDefault());
        final Trade cachedTrade = Trade.builder().executionTime(laterDate).build();
        final Trade receivedTrade = Trade.builder().currencyPair(currencyPair).executionTime(earlierDate).build();

        when(cache.get(eq(currencyPair))).thenReturn(cachedTrade);

        sender.processTrade(receivedTrade);

        verify(cache, times(1)).get(eq(currencyPair));
        verify(cache, never()).put(any(), any());
        verify(converter, never()).convert(any(), any());
    }

    @Test
    public void testUpdateSentWhenReceivedTradeIsMoreRecent() {
        final CurrencyPair currencyPair = CurrencyPair.of("EUR", "GBP");
        final ZonedDateTime laterDate = ZonedDateTime.of(LocalDateTime.MAX, ZoneId.systemDefault());
        final ZonedDateTime earlierDate = ZonedDateTime.of(LocalDateTime.MIN, ZoneId.systemDefault());
        final Trade cachedTrade = Trade.builder().executionTime(earlierDate).build();
        final Trade receivedTrade = Trade.builder().currencyPair(currencyPair).executionTime(laterDate).build();
        final FxUpdate fxUpdate = new FxUpdate();

        when(cache.get(eq(currencyPair))).thenReturn(cachedTrade);
        when(converter.convert(eq(receivedTrade), eq(cachedTrade))).thenReturn(fxUpdate);

        sender.processTrade(receivedTrade);

        verify(cache, times(1)).get(eq(currencyPair));
        verify(cache, times(1)).put(currencyPair, receivedTrade);
        verify(converter, times(1)).convert(receivedTrade, cachedTrade);
        verify(frontEndSocket, times(1)).convertAndSend(anyString(), eq(fxUpdate));
    }

    @Test
    public void testUpdateSentWhenNoCachedTradeExists() {
        final CurrencyPair currencyPair = CurrencyPair.of("EUR", "GBP");
        final ZonedDateTime laterDate = ZonedDateTime.of(LocalDateTime.MAX, ZoneId.systemDefault());
        final Trade receivedTrade = Trade.builder().currencyPair(currencyPair).executionTime(laterDate).build();
        final FxUpdate fxUpdate = new FxUpdate();

        when(cache.get(eq(currencyPair))).thenReturn(null);
        when(converter.convert(eq(receivedTrade), eq(null))).thenReturn(fxUpdate);

        sender.processTrade(receivedTrade);

        verify(cache, times(1)).get(eq(currencyPair));
        verify(cache, times(1)).put(currencyPair, receivedTrade);
        verify(converter, times(1)).convert(receivedTrade, null);
        verify(frontEndSocket, times(1)).convertAndSend(anyString(), eq(fxUpdate));
    }
}
