package com.trendyol.linkconverter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record WeblinkRequestDto(
        @Schema(description = "Weblink to convert", example = "https://www.trendyol.com/brand/name-p-1925865")
        @NotBlank
        String weblink
) implements Serializable {
}
