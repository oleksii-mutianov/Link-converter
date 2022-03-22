package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.function.Predicate;

@Component
public class DefaultWeblinkStrategy implements WebLinkStrategy {

    @Override
    public String createDeepLink(UriComponents uriComponents) {
        return "ty://?Page=Home";
    }

    @Override
    public Predicate<UriComponents> isWebLinkApplicable() {
        return uriComponents -> false; // We can choose default strategy only manually
    }
}
