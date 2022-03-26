package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.web.util.UriComponents;

public interface DeeplinkStrategy {

    /**
     * Returns converted weblink from deeplink
     */
    String getWeblink(UriComponents deeplinkUri);

    /**
     * Returns deeplink page that supported by this strategy
     */
    DeeplinkPage getApplicableDeeplinkPage();

}
