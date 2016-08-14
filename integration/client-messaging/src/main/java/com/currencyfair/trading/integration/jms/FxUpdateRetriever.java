package com.currencyfair.trading.integration.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jasongermaine.
 */
@Component
public class FxUpdateRetriever {

    private final SpotFxCache cache;
    private final FxUpdateConverter converter;


    @Autowired
    public FxUpdateRetriever(final SpotFxCache cache, final FxUpdateConverter converter) {
        this.cache = cache;
        this.converter = converter;
    }

    public List<FxUpdate> retrieveFxUpdates() {
        return cache.values().stream().map(converter::convert).collect(Collectors.toList());
    }
}
