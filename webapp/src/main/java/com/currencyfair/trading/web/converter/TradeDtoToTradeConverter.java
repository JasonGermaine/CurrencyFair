package com.currencyfair.trading.web.converter;

import com.currencyfair.trading.domain.FxPair;
import com.currencyfair.trading.domain.Trade;
import com.currencyfair.trading.intf.TradeDTO;
import org.springframework.core.convert.converter.Converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author jasongermaine.
 */
public class TradeDtoToTradeConverter implements Converter<TradeDTO, Trade> {

    @Override
    public Trade convert(final TradeDTO dto) {
        if (dto == null) {
            return null;
        }

        final FxPair fxPair = FxPair.of(dto.getCurrencyFrom(), dto.getCurrencyTo());
        final ZonedDateTime tradeTime = ZonedDateTime.ofInstant(dto.getTimePlaced().toInstant(), ZoneId.systemDefault());
        return Trade.builder()
                .userId(dto.getUserId())
                .fxPair(fxPair)
                .buyPrice(dto.getAmountBuy())
                .sellPrice(dto.getAmountSell())
                .fxRate(dto.getRate())
                .executionTime(tradeTime)
                .originatingCountry(dto.getOriginatingCountry())
                .build();
    }
}
