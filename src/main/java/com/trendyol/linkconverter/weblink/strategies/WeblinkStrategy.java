package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.web.util.UriComponents;

/**
 * Base interface for weblink strategies.
 */
public interface WeblinkStrategy {

    /**
     * Returns converted deeplink from weblink
     */
    String getDeeplink(UriComponents weblinkUri);

    /**
     * Returns is this strategy can handle this weblink
     */
    default boolean isWeblinkApplicable(UriComponents weblinkUri) {
        return false;
    }

}
