package com.trendyol.linkconverter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public record WeblinkResponseDto(
        @Schema(description = "Converted weblink", example = "https://www.trendyol.com/brand/name-p-1925865")
        String weblink
) implements Serializable {
}
