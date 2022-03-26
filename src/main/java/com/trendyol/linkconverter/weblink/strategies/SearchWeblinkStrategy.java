package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.constants.Deeplink;
import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SearchWeblinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    /**
     * Converts search page weblink to deeplink
     */
    @Override
    protected String createNewResponseLink(UriComponents weblinkUri) {
        var queryParams = weblinkUri.getQueryParams();

        var deeplinkUri = UriComponentsBuilder.fromUriString(Deeplink.BASE_URI)
                .queryParam(Deeplink.QueryParams.PAGE, DeeplinkPage.SEARCH.getValue())
                .queryParam(Deeplink.QueryParams.QUERY, queryParams.get(Weblink.QueryParam.QUERY))
                .build();

        return deeplinkUri.toString();
    }

    /**
     * Returns true if the weblink is a search weblink.
     * It means that the weblink has the only one segment "/sr".
     * For example: https://www.trendyol.com/sr?q=elbise
     */
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
