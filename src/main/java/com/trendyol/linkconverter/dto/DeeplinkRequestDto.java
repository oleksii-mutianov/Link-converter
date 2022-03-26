package com.trendyol.linkconverter.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record DeeplinkRequestDto(
        @Schema(description = "Deeplink to convert", example = "ty://?Page=Product&ContentId=1925865")
        @NotBlank
        String deeplink
) implements Serializable {
}
