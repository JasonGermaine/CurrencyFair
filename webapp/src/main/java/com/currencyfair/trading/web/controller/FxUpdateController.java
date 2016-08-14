package com.currencyfair.trading.web.controller;

import com.currencyfair.trading.integration.jms.FxUpdate;
import com.currencyfair.trading.web.doc.DocumentedApi;
import com.currencyfair.trading.web.service.FxUpdateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author jasongermaine.
 */
@RestController
@DocumentedApi
@Slf4j
public class FxUpdateController {

    private final FxUpdateService service;

    @Autowired
    public FxUpdateController(final FxUpdateService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/fx", method = RequestMethod.GET)
    public ResponseEntity<List<FxUpdate>> getFxUpdates() {
        log.info("Received request to retrieve fx updates");
        List<FxUpdate> updates = service.retrieveUpdates();
        return ResponseEntity.ok(updates);
    }
}
