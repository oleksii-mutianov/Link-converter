package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.constants.Deeplink;
import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Strategy for search deeplinks.
 */
@Component
public class SearchDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    /**
     * Converts search page deeplink to weblink
     *
     * @param deeplinkUri deeplink to convert
     * @return weblink
     */
    @Override
    protected String createNewResponseLink(UriComponents deeplinkUri) {
        var queryParams = deeplinkUri.getQueryParams();

        var weblinkUri = UriComponentsBuilder.fromUriString(Weblink.BASE_URI)
                .pathSegment(Weblink.PathSegments.SR)
                .queryParam(Weblink.QueryParam.QUERY, queryParams.get(Deeplink.QueryParams.QUERY))
                .build();

        return weblinkUri.toString();
    }

    /**
     * Returns {@link DeeplinkPage#SEARCH} that means that this strategy is applicable for search page deeplinks
     *
     * @return {@link DeeplinkPage#SEARCH}
     */
    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.SEARCH;
    }
}
