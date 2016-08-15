package com.currencyfair.trading.web.converter;

import com.currencyfair.trading.domain.model.Ask;
import com.currencyfair.trading.domain.model.Bid;
import com.currencyfair.trading.domain.model.CurrencyPair;
import com.currencyfair.trading.domain.model.Trade;
import com.currencyfair.trading.intf.TradeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author jasongermaine.
 */
@Slf4j
public class TradeDtoToTradeConverter implements Converter<TradeDTO, Trade> {

    @Override
    public Trade convert(final TradeDTO dto) {
        if (dto == null) {
            return null;
        }

        log.debug("Converting TradeDTO to Trade. TradeDTO={}", dto);
        final CurrencyPair currencyPair = CurrencyPair.of(dto.getCurrencyFrom(), dto.getCurrencyTo());
        final ZoneId zone = ZoneId.of("UTC");
        final ZonedDateTime tradeTime = LocalDateTime.ofInstant(dto.getTimePlaced().toInstant(), zone).atZone(zone);
        return Trade.builder()
                .userId(dto.getUserId())
                .currencyPair(currencyPair)
                .ask(Ask.of(dto.getAmountBuy()))
                .bid(Bid.of(dto.getAmountSell()))
                .fxRate(dto.getRate())
                .executionTime(tradeTime)
                .originatingCountry(dto.getOriginatingCountry())
                .build();
    }
}
