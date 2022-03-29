package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.ErrorDetailsDto;
import com.trendyol.linkconverter.dto.ValidationErrorDetailsDto;
import com.trendyol.linkconverter.exception.TrendyolCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller that handles exceptions.
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandlerController {

    /**
     * Handles {@link MethodArgumentNotValidException} thrown when validation fails.
     *
     * @param e exception
     * @return {@link ValidationErrorDetailsDto}
     */
    @ExceptionHandler
    public ResponseEntity<ErrorDetailsDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new ValidationErrorDetailsDto(e.getBindingResult().getFieldErrors()).getResponseEntity();
    }

    /**
     * Handles {@link TrendyolCustomException} and all subclasses, that contain error message and http status.
     *
     * @param e exception
     * @return {@link ErrorDetailsDto}
     */
    @ExceptionHandler
    public ResponseEntity<ErrorDetailsDto> handleTrendyolCustomException(TrendyolCustomException e) {
        return new ErrorDetailsDto(e.getMessage(), e.getHttpStatus()).getResponseEntity();
    }
}
