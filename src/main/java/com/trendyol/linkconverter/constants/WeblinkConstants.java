package com.trendyol.linkconverter.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Some constants for web links.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WeblinkConstants {

    public static final String DEFAULT_PAGE = "https://www.trendyol.com";
    public static final String BASE_URI = DEFAULT_PAGE;

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class PathSegments {
        public static final String SR = "sr";
        public static final String BRAND = "brand";
        public static final String PRODUCT_DELIMITER = "-p-";
        public static final String NAME_P = "name-p-";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class QueryParam {
        public static final String QUERY = "q";
    }
}
