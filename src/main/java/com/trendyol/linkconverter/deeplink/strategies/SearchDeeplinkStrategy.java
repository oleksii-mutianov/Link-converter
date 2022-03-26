package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.constants.Deeplink;
import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SearchDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    @Override
    protected String createNewResponseLink(UriComponents deeplinkUri) {
        var queryParams = deeplinkUri.getQueryParams();

        var weblinkUri = UriComponentsBuilder.fromUriString(Weblink.BASE_URI)
                .pathSegment(Weblink.PathSegments.SR)
                .queryParam(Weblink.QueryParam.QUERY, queryParams.get(Deeplink.QueryParams.QUERY))
                .build();

        return weblinkUri.toString();
    }

    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.SEARCH;
    }
}
