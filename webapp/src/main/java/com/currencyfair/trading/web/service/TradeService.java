package com.currencyfair.trading.web.service;

import com.currencyfair.trading.domain.TradeProcessor;
import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.domain.model.TradeStatistic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author jasongermaine.
 */
@Service
@Slf4j
public class TradeService {

    private final TradeProcessor processor;
    private final JmsTemplate jmsTemplate;
    private final String queueName;

    @Autowired
    public TradeService(final TradeProcessor processor,
                        final JmsTemplate jmsTemplate,
                        @Value("${trading.jms.queue.name}") final String queueName) {
        this.processor = processor;
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    public Long processTrade(final Trade trade) {
        log.info("Sending Trade={} to Queue={}", trade, queueName);
        jmsTemplate.convertAndSend(queueName, trade);
        return processor.process(trade);
    }

    public TradeStatistic retrieveTradeStatistics() {
        log.info("Retrieving trade statistics for current date");
        final ZoneId zone = ZoneId.of("UTC");
        final ZonedDateTime start = LocalDate.now().atStartOfDay(zone);
        final ZonedDateTime end = LocalDateTime.now().atZone(zone);
        return processor.retrieveProcessedTradeStatistics(start, end);
    }
}
