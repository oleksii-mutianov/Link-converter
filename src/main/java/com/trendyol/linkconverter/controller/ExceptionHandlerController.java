package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.ErrorDetailsDto;
import com.trendyol.linkconverter.dto.ValidationErrorDetailsDto;
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
     * Handles {@link MethodArgumentNotValidException}
     *
     * @param e exception
     * @return {@link ResponseEntity<ErrorDetailsDto>}
     */
    @ExceptionHandler
    public ResponseEntity<ErrorDetailsDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return new ValidationErrorDetailsDto(e.getBindingResult().getFieldErrors()).getResponseEntity();
    }

}
