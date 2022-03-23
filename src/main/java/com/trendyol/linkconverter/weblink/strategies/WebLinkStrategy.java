package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.web.util.UriComponents;

public interface WebLinkStrategy {

    String createDeepLink(UriComponents webLinkUri);

    boolean isWebLinkApplicable(UriComponents webLinkUri);

}
