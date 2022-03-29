package com.trendyol.linkconverter.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

/**
 * Container for error details of exceptions.
 */
@Getter
public class ErrorDetailsDto {
    /**
     * Time when exception occurred.
     */
    private LocalDateTime timestamp;
    /**
     * Exception message.
     */
    private String message;
    /**
     * Exception http status.
     */
    private Integer status;
    /**
     * Exception http status details
     */
    private String error;

    public ErrorDetailsDto(String message, HttpStatus httpStatus) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
    }

    /**
     * Returns a {@link ResponseEntity} with the error details.
     *
     * @return {@link ResponseEntity}
     */
    @JsonIgnore
    public ResponseEntity<ErrorDetailsDto> getResponseEntity() {
        return ResponseEntity.status(status).body(this);
    }
}
