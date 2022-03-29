package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

/**
 * Default deeplink strategy when no strategy is found.
 */
@Component
public class DefaultDeeplinkStrategy implements DeeplinkStrategy {

    /**
     * Returns default weblink.
     *
     * @param deeplinkUri deeplink uri to convert
     * @return default weblink page
     */
    @Override
    public String getWeblink(UriComponents deeplinkUri) {
        return Weblink.DEFAULT_PAGE;
    }

    /**
     * Returns {@link DeeplinkPage#DEFAULT} that means that this strategy appropriate for all other pages.
     *
     * @return {@link DeeplinkPage#DEFAULT}
     */
    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.DEFAULT;
    }
}
