package com.trendyol.linkconverter.weblink.strategies;

import com.google.common.base.CharMatcher;
import com.trendyol.linkconverter.weblink.persistence.WeblinkRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
public class ProductDetailsWeblinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    public ProductDetailsWeblinkStrategy(WeblinkRepository weblinkRepository) {
        super(weblinkRepository);
    }

    @Override
    public String createNewDeeplink(UriComponents weblinkUri) {
        var pathSegments = weblinkUri.getPathSegments();
        var lastSegment = pathSegments.get(pathSegments.size() - 1);
        var contentId = CharMatcher.inRange('0', '9').retainFrom(lastSegment);
        var queryParams = weblinkUri.getQueryParams();

        var deeplinkUri = UriComponentsBuilder.fromUriString("")
                .scheme("ty//")
                .queryParam("Page", "Product")
                .queryParam("ContentId", contentId)
                .queryParamIfPresent("CampaignId", Optional.ofNullable(queryParams.get("boutiqueId")))
                .queryParamIfPresent("MerchantId", Optional.ofNullable(queryParams.get("merchantId")))
                .build();

        return deeplinkUri.toString();
    }

    @Override
    public boolean isWeblinkApplicable(UriComponents weblinkUri) {
        var pathSegments = weblinkUri.getPathSegments();
        var applicableSegmentCount = 2;
        if (pathSegments.size() != applicableSegmentCount) {
            return false;
        }

        var lastSegmentIndex = 1;
        return pathSegments.get(lastSegmentIndex).contains("-p-");
    }
}
