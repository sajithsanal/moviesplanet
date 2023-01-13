package com.moviesplanet.theater.inbound.controller;

import com.moviesplanet.common.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class TheaterInboundControllerAdvice {

    Logger logger = LoggerFactory.getLogger(TheaterInboundControllerAdvice.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleRuntimeException(Exception ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "ERROR");
        body.put("message", "There is an issue with application. Please contact support");
        logger.error("Application Error --> " + ex.getMessage(), ex);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "ERROR");
        body.put("message", ex.getMessage());
        logger.error("Validation Error --> " + ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
