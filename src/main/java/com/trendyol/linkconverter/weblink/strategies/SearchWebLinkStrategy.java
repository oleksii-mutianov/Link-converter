package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.weblink.persistence.WeblinkRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SearchWebLinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    public SearchWebLinkStrategy(WeblinkRepository weblinkRepository) {
        super(weblinkRepository);
    }

    @Override
    public String createNewDeepLink(UriComponents webLinkUri) {
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
        var applicableSegmentCount = 1;
        if (pathSegments.size() != applicableSegmentCount) {
            return false;
        }
        return pathSegments.get(0).equals("sr");
    }
}
