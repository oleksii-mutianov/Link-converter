package com.trendyol.linkconverter.deeplink.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum DeeplinkPage {
    PRODUCT("Product"),
    SEARCH("Search"),
    DEFAULT(null);

    private final String value;

    public static DeeplinkPage fromString(String string) {
        return Arrays.stream(values())
                .filter(deeplinkPage -> Objects.equals(deeplinkPage.value, string))
                .findFirst()
                .orElse(DEFAULT);
    }
}
