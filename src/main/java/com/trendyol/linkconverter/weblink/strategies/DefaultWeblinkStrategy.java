package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.constants.Deeplink;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

/**
 * Default weblink strategy when no strategy is found.
 */
@Component
public class DefaultWeblinkStrategy implements WeblinkStrategy {

    /**
     * Returns default deeplink.
     */
    @Override
    public String getDeeplink(UriComponents weblinkUri) {
        return Deeplink.DEFAULT_PAGE;
    }

}
