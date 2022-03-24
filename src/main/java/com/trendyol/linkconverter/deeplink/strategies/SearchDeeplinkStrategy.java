package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.deeplink.persistence.DeeplinkRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class SearchDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    public SearchDeeplinkStrategy(DeeplinkRepository deeplinkRepository) {
        super(deeplinkRepository);
    }

    @Override
    public String createNewWeblink(UriComponents deeplinkUri) {
        var queryParams = deeplinkUri.getQueryParams();

        var weblinkUri = UriComponentsBuilder.fromPath("https://www.trendyol.com")
                .path("sr")
                .queryParam("q", queryParams.get("Query"))
                .build();

        return weblinkUri.toString();
    }

    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.SEARCH;
    }
}
