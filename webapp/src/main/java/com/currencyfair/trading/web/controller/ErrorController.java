package com.currencyfair.trading.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author jasongermaine.
 */
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> handleRetrievalException(final Exception exception) {
        log.error("An unexpected exception has occurred.", exception);
        return ResponseEntity.status(500).build();
    }
}
