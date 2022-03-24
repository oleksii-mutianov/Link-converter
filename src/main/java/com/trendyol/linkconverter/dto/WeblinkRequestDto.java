package com.trendyol.linkconverter.dto;

import javax.validation.constraints.NotBlank;

public record WeblinkRequestDto(@NotBlank String weblink) {
}
