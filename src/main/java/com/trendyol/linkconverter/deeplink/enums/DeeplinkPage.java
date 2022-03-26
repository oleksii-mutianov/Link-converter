package com.trendyol.linkconverter.deeplink.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * Enum represents the supported deeplink pages.
 */
@Getter
@RequiredArgsConstructor
public enum DeeplinkPage {
    /**
     * Product page.
     */
    PRODUCT("Product"),
    /**
     * Search page.
     */
    SEARCH("Search"),
    /**
     * All other pages currently not supported.
     */
    DEFAULT(null);

    /**
     * The page name.
     */
    private final String value;

    /**
     * Returns the enum value of the given string.
     *
     * @param string the string value
     * @return the enum value
     */
    public static DeeplinkPage fromString(String string) {
        return Arrays.stream(values())
                .filter(deeplinkPage -> Objects.equals(deeplinkPage.value, string))
                .findFirst()
                .orElse(DEFAULT);
    }
}
