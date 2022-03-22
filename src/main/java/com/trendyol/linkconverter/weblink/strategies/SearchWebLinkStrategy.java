package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.function.Predicate;

@Component
public class SearchWebLinkStrategy implements WebLinkStrategy {
    @Override
    public String createDeepLink(UriComponents uriComponents) {
        var queryParams = uriComponents.getQueryParams();

        var deepLinkUri = UriComponentsBuilder.fromUriString("")
                .scheme("ty//")
                .queryParam("Page", "Search")
                .queryParam("Query", queryParams.get("q"))
                .build();

        return deepLinkUri.toString();
    }

    @Override
    public Predicate<UriComponents> isWebLinkApplicable() {
        return uriComponents -> {
            var pathSegments = uriComponents.getPathSegments();
            if (pathSegments.isEmpty()) {
                return false;
            }
            return pathSegments.get(0).equals("sr");
        };
    }
}
