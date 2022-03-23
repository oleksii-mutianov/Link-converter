package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.web.util.UriComponents;

public interface WebLinkStrategy {

    String createDeepLink(UriComponents webLinkUri);

    default boolean isWebLinkApplicable(UriComponents webLinkUri) {
        return false;
    }

    default boolean isCacheAvailable() {
        return true;
    }

}
