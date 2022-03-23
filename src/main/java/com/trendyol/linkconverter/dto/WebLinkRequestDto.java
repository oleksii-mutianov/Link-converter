package com.trendyol.linkconverter.dto;

import javax.validation.constraints.NotBlank;

public record WebLinkRequestDto(@NotBlank String webLink) {
}
