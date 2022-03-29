package com.trendyol.linkconverter.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Error messages constants.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {
    public static final String MULTIPLE_CONTENT_ID = "Multiple 'ContentId' parameter not supported for deeplinks";
    public static final String MULTIPLE_PAGE = "Multiple 'Page' parameters in deeplinks not supported";
    public static final String INVALID_WEBLINK = "Invalid weblink";
    public static final String INVALID_DEEPLINK = "Invalid deeplink";
}
