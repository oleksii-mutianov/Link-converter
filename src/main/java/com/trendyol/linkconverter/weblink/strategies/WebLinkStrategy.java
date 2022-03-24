package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.web.util.UriComponents;

public interface WebLinkStrategy {

    String getDeepLink(UriComponents webLinkUri);

    default boolean isWebLinkApplicable(UriComponents webLinkUri) {
        return false;
    }

}
