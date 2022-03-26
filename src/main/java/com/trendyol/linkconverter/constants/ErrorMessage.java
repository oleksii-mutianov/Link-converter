package com.trendyol.linkconverter.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessage {
    public static final String MULTIPLE_CONTENT_ID = "Multiple 'ContentId' parameter not supported for deeplinks";
    public static final String MULTIPLE_PAGE = "Multiple 'Page' parameters in deeplinks not supported";
}
