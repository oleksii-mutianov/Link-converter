package com.trendyol.linkconverter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Base class for all custom exceptions.
 */
public abstract class TrendyolCustomException extends RuntimeException {

    /**
     * Http status code.
     */
    @Getter
    private final HttpStatus httpStatus;

    protected TrendyolCustomException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
