package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

@Component
public class DefaultDeeplinkStrategy implements DeeplinkStrategy {

    @Override
    public String getWeblink(UriComponents deeplinkUri) {
        return Weblink.DEFAULT_PAGE;
    }

    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.DEFAULT;
    }
}
