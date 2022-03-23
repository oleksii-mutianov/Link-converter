package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SearchWebLinkStrategy implements WebLinkStrategy {
    @Override
    public String createDeepLink(UriComponents webLinkUri) {
        var queryParams = webLinkUri.getQueryParams();

        var deepLinkUri = UriComponentsBuilder.fromUriString("")
                .scheme("ty//")
                .queryParam("Page", "Search")
                .queryParam("Query", queryParams.get("q"))
                .build();

        return deepLinkUri.toString();
    }

    @Override
    public boolean isWebLinkApplicable(UriComponents webLinkUri) {
        var pathSegments = webLinkUri.getPathSegments();
        if (pathSegments.size() != 1) {
            return false;
        }
        return pathSegments.get(0).equals("sr");
    }
}
