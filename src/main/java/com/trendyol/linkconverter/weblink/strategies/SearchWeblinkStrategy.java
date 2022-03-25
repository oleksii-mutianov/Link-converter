package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SearchWeblinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    @Override
    protected String createNewResponseLink(UriComponents weblinkUri) {
        var queryParams = weblinkUri.getQueryParams();

        var deeplinkUri = UriComponentsBuilder.fromUriString("ty://")
                .queryParam("Page", "Search")
                .queryParam("Query", queryParams.get("q"))
                .build();

        return deeplinkUri.toString();
    }

    @Override
    public boolean isWeblinkApplicable(UriComponents weblinkUri) {
        var pathSegments = weblinkUri.getPathSegments();
        var applicableSegmentCount = 1;
        if (pathSegments.size() != applicableSegmentCount) {
            return false;
        }
        return pathSegments.get(0).equals("sr");
    }
}
