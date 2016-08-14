package com.currencyfair.trading.web.controller;

import com.currencyfair.trading.intf.DateDeserializationException;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jasongermaine.
 */
@ControllerAdvice
@Slf4j
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> handleUnkownException(final Exception exception) {
        log.error("An unexpected exception has occurred.", exception);
        return ResponseEntity.status(500).build();
    }

    @ExceptionHandler(value = DateDeserializationException.class)
    public ResponseEntity<Void> handleDateDeserializationException(final DateDeserializationException exception) {
        log.error("A date deserialization exception has occurred.", exception);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(final MethodArgumentNotValidException exception) {
        log.error("A validation exception has occurred.", exception);
        final BindingResult bindingResult = exception.getBindingResult();
        final List<String> errorMessages = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(Joiner.on(',').join(errorMessages), HttpStatus.BAD_REQUEST);
    }

}
