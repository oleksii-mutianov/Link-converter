package com.trendyol.linkconverter.dto;

import com.trendyol.linkconverter.validation.ValidTrendyolDeeplink;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Request DTO for deeplink conversion
 */
public record DeeplinkRequestDto(
        @Schema(description = "Deeplink to convert", example = "ty://?Page=Product&ContentId=1925865")
        @NotBlank
        @ValidTrendyolDeeplink
        String deeplink
) implements Serializable {
}
