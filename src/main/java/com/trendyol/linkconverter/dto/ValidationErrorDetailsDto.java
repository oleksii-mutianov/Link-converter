package com.trendyol.linkconverter.dto;

import lombok.Getter;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.Map;

/**
 * Error details for validation errors.
 */
@Getter
public class ValidationErrorDetailsDto extends ErrorDetailsDto {
    /**
     * Map containing field names and error messages.
     */
    private final Map<String, List<String>> validationErrors;

    public ValidationErrorDetailsDto(List<FieldError> fieldErrors) {
        super("Validation failed", HttpStatus.UNPROCESSABLE_ENTITY);
        this.validationErrors = fieldErrors
                .stream()
                .collect(StreamUtils.toMultiMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
