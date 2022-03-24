package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
public class ProductDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    @Override
    protected String createNewResponseLink(UriComponents deepLinkUri) {
        var queryParams = deepLinkUri.getQueryParams();

        var weblinkUri = UriComponentsBuilder.fromPath("https://www.trendyol.com")
                .path("brand/name-p-" + queryParams.get("ContentId"))
                .queryParamIfPresent("boutiqueId", Optional.ofNullable(queryParams.get("CampaignId")))
                .queryParamIfPresent("merchantId", Optional.ofNullable(queryParams.get("MerchantId")))
                .build();

        return weblinkUri.toString();
    }

    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.PRODUCT;
    }
}
