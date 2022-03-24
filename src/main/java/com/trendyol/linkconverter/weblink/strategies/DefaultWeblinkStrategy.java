package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

@Component
public class DefaultWeblinkStrategy implements WeblinkStrategy {

    @Override
    public String getDeeplink(UriComponents weblinkUri) {
        return "ty://?Page=Home";
    }

}
