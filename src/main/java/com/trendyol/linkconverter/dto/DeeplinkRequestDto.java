package com.trendyol.linkconverter.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record DeeplinkRequestDto(@NotBlank String deeplink) implements Serializable {
}
