package com.trendyol.linkconverter.exception;

import org.springframework.http.HttpStatus;

/**
 * Exception for invalid parameter.
 */
public class InvalidParameterException extends TrendyolCustomException {
    public InvalidParameterException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
