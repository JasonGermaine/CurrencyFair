package com.currencyfair.trading.integration.db;

import com.currencyfair.trading.domain.TradeProcessor;
import com.currencyfair.trading.domain.model.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author jasongermaine.
 */

@Component
@Slf4j
@ManagedResource(objectName = "TradingApp:name=DB")
public class DatabaseTradeProcessor implements TradeProcessor {

    private final TradeRepository tradeRepository;
    private final Function<Trade, TradeEntity> tradeToEntityFunction;
    private final Function<TradeEntity, Trade> entityToTradeFunction;
    private final TradeStatisticCalculator tradeStatisticCalculator;

    @Autowired
    public DatabaseTradeProcessor(final TradeRepository tradeRepository,
                                  final Function<Trade, TradeEntity> tradeToEntityFunction,
                                  final Function<TradeEntity, Trade> entitToTradeFunction,
                                  final TradeStatisticCalculator tradeStatisticCalculator) {
        this.tradeRepository = tradeRepository;
        this.tradeToEntityFunction = tradeToEntityFunction;
        this.entityToTradeFunction = entitToTradeFunction;
        this.tradeStatisticCalculator = tradeStatisticCalculator;
    }

    @Override
    @CacheEvict
    public Long process(final Trade trade) {
        final TradeEntity entity = tradeToEntityFunction.apply(trade);

        log.info("Persisting TradeEntity={}", entity);
        return tradeRepository.save(entity).getTradeId();
    }

    @Override
    @Cacheable("stats")
    public TradeStatistic retrieveProcessedTradeStatistics(final ZonedDateTime start, final ZonedDateTime end) {
        final List<TradeEntity> entities = tradeRepository.findByExecutionTimeBetween(Timestamp.from(start.toInstant()), Timestamp.from(end.toInstant()));

        final List<Trade> trades = entities.stream().map(entityToTradeFunction).collect(Collectors.toList());

        return tradeStatisticCalculator.calculate(trades);
    }

    @ManagedOperation(description = "Show data in DB.")
    public List<String> displayTrades() {
        return tradeRepository.findAll().stream().map(TradeEntity::toString).collect(Collectors.toList());
    }

    @PostConstruct
    private void init() {
        final CurrencyPair pair = CurrencyPair.of("EUR", "GBP");
        final Trade tradeOne = Trade.builder()
                .currencyPair(pair)
                .bid(Bid.of(new BigDecimal(0.8634)))
                .ask(Ask.of(new BigDecimal(0.8644)))
                .fxRate(new BigDecimal(0.86339))
                .originatingCountry("IRE")
                .executionTime(ZonedDateTime.of(LocalDate.now(), LocalTime.now().minusHours(3), ZoneId.of("UTC")))
                .userId(0)
                .build();
        final Trade tradeTwo = Trade.builder()
                .currencyPair(pair)
                .bid(Bid.of(new BigDecimal(0.8634)))
                .ask(Ask.of(new BigDecimal(0.8644)))
                .fxRate(new BigDecimal(0.86339))
                .originatingCountry("IRE")
                .executionTime(LocalDateTime.now().atZone(ZoneId.of("UTC")))
                .userId(0)
                .build();
        tradeRepository.save(Arrays.asList(tradeToEntityFunction.apply(tradeOne), tradeToEntityFunction.apply(tradeTwo)));
    }
}
