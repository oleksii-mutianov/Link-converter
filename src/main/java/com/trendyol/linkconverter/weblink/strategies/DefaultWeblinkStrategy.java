package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

@Component
public class DefaultWeblinkStrategy implements WebLinkStrategy {

    @Override
    public String getDeepLink(UriComponents webLinkUri) {
        return "ty://?Page=Home";
    }

}
