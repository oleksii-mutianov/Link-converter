package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

@Component
public class DefaultDeeplinkStrategy implements DeeplinkStrategy {
    @Override
    public String getWeblink(UriComponents deeplinkUri) {
        return "https://www.trendyol.com";
    }

    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.DEFAULT;
    }
}
