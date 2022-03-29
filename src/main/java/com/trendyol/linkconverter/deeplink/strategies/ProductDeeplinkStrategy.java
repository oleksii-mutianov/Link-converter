package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.constants.Deeplink;
import com.trendyol.linkconverter.constants.ErrorMessage;
import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.exception.InvalidParameterException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * Strategy for product deeplinks.
 */
@Component
public class ProductDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    /**
     * Converts product page deeplink to weblink
     *
     * @param deeplinkUri deeplink to be converted
     * @return weblink
     */
    @Override
    protected String createNewResponseLink(UriComponents deeplinkUri) {
        var queryParams = deeplinkUri.getQueryParams();

        if (queryParams.get(Deeplink.QueryParams.CONTENT_ID).size() != 1) {
            throw new InvalidParameterException(ErrorMessage.MULTIPLE_CONTENT_ID);
        }

        var weblinkUri = UriComponentsBuilder.fromUriString(Weblink.BASE_URI)
                .pathSegment(Weblink.PathSegments.BRAND, Weblink.PathSegments.NAME_P + queryParams.get(Deeplink.QueryParams.CONTENT_ID).get(0))
                .queryParamIfPresent(Weblink.QueryParam.BOUTIQUE_ID, Optional.ofNullable(queryParams.get(Deeplink.QueryParams.CAMPAIGN_ID)))
                .queryParamIfPresent(Weblink.QueryParam.MERCHANT_ID, Optional.ofNullable(queryParams.get(Deeplink.QueryParams.MERCHANT_ID)))
                .build();

        return weblinkUri.toString();
    }

    /**
     * Returns {@link DeeplinkPage#PRODUCT} that means that this strategy is applicable for product page deeplink
     *
     * @return {@link DeeplinkPage#PRODUCT}
     */
    @Override
    public DeeplinkPage getApplicableDeeplinkPage() {
        return DeeplinkPage.PRODUCT;
    }
}
