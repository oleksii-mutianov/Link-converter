package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.constants.DeeplinkConstants;
import com.trendyol.linkconverter.constants.WeblinkConstants;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Strategy for search deeplinks.
 */
@Component
public class SearchDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    public SearchDeeplinkStrategy(LinkRepository linkRepository) {
        super(linkRepository);
    }

    /**
     * Converts search page deeplink to weblink
     *
     * @param deeplinkUri deeplink to convert
     * @return weblink
     */
    @Override
    protected String createNewResponseLink(UriComponents deeplinkUri) {
        var queryParams = deeplinkUri.getQueryParams();

        var weblinkUri = UriComponentsBuilder.fromUriString(WeblinkConstants.BASE_URI)
                .pathSegment(WeblinkConstants.PathSegments.SR)
                .queryParam(WeblinkConstants.QueryParam.QUERY, queryParams.get(DeeplinkConstants.QueryParams.QUERY))
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
