package com.trendyol.linkconverter.dto;

import javax.validation.constraints.NotBlank;

public record DeeplinkRequestDto(@NotBlank String deeplink) {
}
