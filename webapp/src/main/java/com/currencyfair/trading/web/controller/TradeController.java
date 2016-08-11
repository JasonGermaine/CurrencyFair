package com.currencyfair.trading.web.controller;

import com.currencyfair.trading.domain.Trade;
import com.currencyfair.trading.intf.TradeDTO;
import com.currencyfair.trading.web.doc.DocumentedApi;
import com.currencyfair.trading.web.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jasongermaine.
 */
@RestController
@RequestMapping("/trade")
@DocumentedApi
@Slf4j
public class TradeController {

    private final TradeService tradeService;
    private final ConversionService conversionService;

    @Autowired
    public TradeController(final TradeService tradeService, final ConversionService conversionService) {
        this.tradeService = tradeService;
        this.conversionService = conversionService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Long> postTrade(@RequestBody TradeDTO tradeDTO) {
        log.info("Received trade POST request");
        final Trade trade = conversionService.convert(tradeDTO, Trade.class);
        final Long tradeId = tradeService.processTrade(trade);
        return ResponseEntity.ok(tradeId);
    }
}
