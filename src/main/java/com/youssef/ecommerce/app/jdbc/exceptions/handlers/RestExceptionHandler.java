package com.youssef.ecommerce.app.jdbc.exceptions.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.NoSuchElementException;


@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<HttpStatus> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        log.error(">>>>> No Such Element Exception ==> " + noSuchElementException);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpStatus> handleInternalServerError(Exception exception) {
        log.error(">>>>> UnHandled Exception ==> " + exception);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
