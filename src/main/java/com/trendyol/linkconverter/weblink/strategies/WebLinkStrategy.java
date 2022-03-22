package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.web.util.UriComponents;

import java.util.function.Predicate;

public interface WebLinkStrategy {

    String createDeepLink(UriComponents uriComponents);

    Predicate<UriComponents> isWebLinkApplicable();

}
