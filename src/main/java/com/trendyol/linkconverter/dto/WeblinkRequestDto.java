package com.trendyol.linkconverter.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public record WeblinkRequestDto(@NotBlank String weblink) implements Serializable {
}
