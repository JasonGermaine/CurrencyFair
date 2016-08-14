package com.currencyfair.trading.web.converter;

import com.currencyfair.trading.domain.model.TradeStatistic;
import com.currencyfair.trading.intf.TradeStatisticDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 * @author jasongermaine.
 */
@Slf4j
public class TradeStatisticToTradeStatisticDtoConverter implements Converter<TradeStatistic, TradeStatisticDTO> {

    @Override
    public TradeStatisticDTO convert(TradeStatistic tradeStatistic) {
        if (tradeStatistic == null) {
            return null;
        }

        final TradeStatisticDTO dto = new TradeStatisticDTO();
        dto.setActiveUsers(tradeStatistic.getActiveUsers());
        dto.setTradesPlaced(tradeStatistic.getTradesPlaced());
        dto.setCurrencyPairFrequency(tradeStatistic.getCurrencyPairFrequency());
        dto.setOriginatingCountryFrequency(tradeStatistic.getOriginatingCountryFrequency());
        return dto;
    }
}
