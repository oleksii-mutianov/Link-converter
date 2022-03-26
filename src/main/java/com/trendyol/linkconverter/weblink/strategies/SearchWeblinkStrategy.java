package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.constants.Deeplink;
import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SearchWeblinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    @Override
    protected String createNewResponseLink(UriComponents weblinkUri) {
        var queryParams = weblinkUri.getQueryParams();

        var deeplinkUri = UriComponentsBuilder.fromUriString(Deeplink.BASE_URI)
                .queryParam(Deeplink.QueryParams.PAGE, DeeplinkPage.SEARCH.getValue())
                .queryParam(Deeplink.QueryParams.QUERY, queryParams.get(Weblink.QueryParam.QUERY))
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
        return pathSegments.get(0).equals(Weblink.PathSegments.SR);
    }
}
