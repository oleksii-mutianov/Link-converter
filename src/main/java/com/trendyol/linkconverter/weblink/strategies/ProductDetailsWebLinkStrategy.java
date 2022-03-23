package com.trendyol.linkconverter.weblink.strategies;

import com.google.common.base.CharMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
public class ProductDetailsWebLinkStrategy implements WebLinkStrategy {
    @Override
    public String createDeepLink(UriComponents webLinkUri) {
        var pathSegments = webLinkUri.getPathSegments();
        var lastSegment = pathSegments.get(pathSegments.size() - 1);
        var contentId = CharMatcher.inRange('0', '9').retainFrom(lastSegment);
        var queryParams = webLinkUri.getQueryParams();

        var deepLinkUri = UriComponentsBuilder.fromUriString("")
                .scheme("ty//")
                .queryParam("Page", "Product")
                .queryParam("ContentId", contentId)
                .queryParamIfPresent("CampaignId", Optional.ofNullable(queryParams.get("boutiqueId")))
                .queryParamIfPresent("MerchantId", Optional.ofNullable(queryParams.get("merchantId")))
                .build();

        return deepLinkUri.toString();
    }

    @Override
    public boolean isWebLinkApplicable(UriComponents webLinkUri) {
        var pathSegments = webLinkUri.getPathSegments();
        var applicableSegmentCount = 2;
        if (pathSegments.size() != applicableSegmentCount) {
            return false;
        }

        var lastSegmentIndex = 1;
        return pathSegments.get(lastSegmentIndex).contains("-p-");
    }
}
