package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Component
public class ProductDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    @Override
    protected String createNewResponseLink(UriComponents deeplinkUri) {
        var queryParams = deeplinkUri.getQueryParams();

        if (queryParams.get("ContentId").size() != 1) {
            throw new RuntimeException("Multiple 'ContentId' parameter not supported for deeplinks");
        }

        var weblinkUri = UriComponentsBuilder.fromUriString("https://www.trendyol.com")
                .path("/brand/name-p-" + queryParams.get("ContentId").get(0))
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
