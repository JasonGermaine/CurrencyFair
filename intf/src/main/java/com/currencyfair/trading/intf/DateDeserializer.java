package com.currencyfair.trading.intf;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author jasongermaine.
 */
public class DateDeserializer extends JsonDeserializer<Date> {

    private final ThreadLocal<SimpleDateFormat> FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(TradeDTO.DATE_TIME_PATTERN);
        }
    };

    @Override
    public Date deserialize(final JsonParser jsonparser, final DeserializationContext deserializationcontext) {
        try {
            return FORMATTER.get().parse(jsonparser.getText());
        } catch (IOException | ParseException e) {
            throw new DateDeserializationException(e);
        }
    }
}
