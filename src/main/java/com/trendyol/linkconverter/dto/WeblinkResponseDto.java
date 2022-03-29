package com.trendyol.linkconverter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

/**
 * Response DTO for deeplink to weblink conversion
 */
public record WeblinkResponseDto(
        @Schema(description = "Converted weblink", example = "https://www.trendyol.com/brand/name-p-1925865")
        String weblink
) implements Serializable {
}
