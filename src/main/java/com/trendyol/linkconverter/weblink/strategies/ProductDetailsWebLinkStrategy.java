package com.trendyol.linkconverter.weblink.strategies;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class ProductDetailsWebLinkStrategy implements WebLinkStrategy {
    @Override
    public String createDeepLink(UriComponents uriComponents) {
        var pathSegments = uriComponents.getPathSegments();
        var queryParams = uriComponents.getQueryParams();
        var lastSegment = pathSegments.get(pathSegments.size() - 1);
        var contentId = lastSegment.replaceAll("\\D+", "");

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
    public Predicate<UriComponents> isWebLinkApplicable() {
        return uriComponents -> {
            var pathSegments = uriComponents.getPathSegments();
            var applicableSegmentCount = 2;
            if (pathSegments.size() != applicableSegmentCount) {
                return false;
            }

            var lastSegmentIndex = 1;
            return pathSegments.get(lastSegmentIndex).contains("-p-");
        };
    }
}
