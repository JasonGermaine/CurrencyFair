package com.currencyfair.trading.web.service;

import com.currencyfair.trading.integration.jms.FxUpdate;
import com.currencyfair.trading.integration.jms.FxUpdateRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jasongermaine.
 */
@Service
public class FxUpdateService {

    private final FxUpdateRetriever fxUpdateRetriever;

    @Autowired
    public FxUpdateService(final FxUpdateRetriever fxUpdateRetriever) {
        this.fxUpdateRetriever = fxUpdateRetriever;
    }

    public List<FxUpdate> retrieveUpdates() {
        return fxUpdateRetriever.retrieveFxUpdates();
    }
}
