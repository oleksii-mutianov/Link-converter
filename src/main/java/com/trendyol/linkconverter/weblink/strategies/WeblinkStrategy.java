package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.web.util.UriComponents;

public interface WeblinkStrategy {

    String getDeeplink(UriComponents weblinkUri);

    default boolean isWeblinkApplicable(UriComponents weblinkUri) {
        return false;
    }

}
