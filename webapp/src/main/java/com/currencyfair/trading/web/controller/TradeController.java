package com.currencyfair.trading.web.controller;

import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.domain.model.TradeStatistic;
import com.currencyfair.trading.intf.TradeDTO;
import com.currencyfair.trading.intf.TradeStatisticDTO;
import com.currencyfair.trading.web.doc.DocumentedApi;
import com.currencyfair.trading.web.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author jasongermaine.
 */
@RestController
@DocumentedApi
@Slf4j
@RequestMapping("/api")
public class TradeController {

    private final TradeService tradeService;
    private final ConversionService conversionService;

    @Autowired
    public TradeController(final TradeService tradeService, final ConversionService conversionService) {
        this.tradeService = tradeService;
        this.conversionService = conversionService;
    }

    @RequestMapping(value = "/trade", method = RequestMethod.POST)
    public ResponseEntity<Long> postTrade(@Valid @RequestBody TradeDTO tradeDTO) {
        MDC.put("user", Long.toString(tradeDTO.getUserId()));
        try {
            final Trade trade = conversionService.convert(tradeDTO, Trade.class);
            log.info("Consumed Trade={}", trade);

            final Long tradeId = tradeService.processTrade(trade);
            log.info("Finished processing Trade={}", trade);

            log.info("Returning TradeId={}", tradeId);
            return ResponseEntity.ok(tradeId);
        } finally {
            MDC.clear();
        }
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    public ResponseEntity<TradeStatisticDTO> retrieveTradeStatistics() {
        log.info("Retrieved request for trade statistics");
        final TradeStatistic tradeStatistic = tradeService.retrieveTradeStatistics();

        log.info("Returning TradeStatistic={}", tradeStatistic);
        final TradeStatisticDTO dto = conversionService.convert(tradeStatistic, TradeStatisticDTO.class);
        return ResponseEntity.ok(dto);
    }
}
