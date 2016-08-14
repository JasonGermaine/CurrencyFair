package com.currencyfair.trading.integration.jms;

import com.currencyfair.trading.domain.model.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @author jasongermaine.
 */
@Component
@Slf4j
public class FxUpdateSender {

    private static final String DESTINATION = "/fx/update";

    private final SimpMessagingTemplate frontEndSocket;
    private final SpotFxCache cache;
    private final FxUpdateConverter converter;

    @Autowired
    public FxUpdateSender(final SimpMessagingTemplate frontEndSocket, final SpotFxCache cache, final FxUpdateConverter converter) {
        this.frontEndSocket = frontEndSocket;
        this.cache = cache;
        this.converter = converter;
    }


    @JmsListener(destination = "trade-queue", containerFactory = "jmsListenerContainerFactory")
    public void processTrade(final Trade receivedTrade) {
        log.info("Received Trade={}", receivedTrade);
        final Trade cachedTrade = cache.get(receivedTrade.getCurrencyPair());
        if (cachedTrade == null || receivedTrade.isMoreRecent(cachedTrade)) {
            cache.put(receivedTrade.getCurrencyPair(), receivedTrade);
            final FxUpdate fxUpdate = converter.convert(receivedTrade, cachedTrade);

            log.info("Sending FxUpdate={} to destination={}", fxUpdate, DESTINATION);
            frontEndSocket.convertAndSend(DESTINATION, fxUpdate);
        }
    }
}
