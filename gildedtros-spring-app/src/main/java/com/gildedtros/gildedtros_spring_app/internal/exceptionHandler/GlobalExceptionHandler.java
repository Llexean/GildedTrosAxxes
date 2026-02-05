package com.gildedtros.gildedtros_spring_app.internal.exceptionHandler;

import com.gildedtros.gildedtros_spring_app.internal.exception.InvalidItemNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidItemNameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExceptions(InvalidItemNameException e){
        logger.error(e.getMessage());
        return e.getMessage();
    }
}
