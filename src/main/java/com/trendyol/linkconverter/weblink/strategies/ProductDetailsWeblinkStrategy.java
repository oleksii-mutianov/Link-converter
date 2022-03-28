package com.trendyol.linkconverter.weblink.strategies;

import com.google.common.base.CharMatcher;
import com.trendyol.linkconverter.constants.Deeplink;
import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.regex.Pattern;

@Component
public class ProductDetailsWeblinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    private static final String ANY_SYMBOLS = ".+";
    private static final String ANY_DIGITS = "[0-9]+";
    private static final Pattern VALID_PRODUCT_LINK_SEGMENT_PATTERN =
            Pattern.compile(ANY_SYMBOLS + Weblink.PathSegments.PRODUCT_DELIMITER + ANY_DIGITS);

    /**
     * Converts product page weblink to deeplink
     */
    @Override
    protected String createNewResponseLink(UriComponents weblinkUri) {
        var pathSegments = weblinkUri.getPathSegments();
        var lastSegment = pathSegments.get(pathSegments.size() - 1);
        var contentId = CharMatcher.inRange('0', '9').retainFrom(lastSegment);
        var queryParams = weblinkUri.getQueryParams();

        var deeplinkUri = UriComponentsBuilder.fromUriString(Deeplink.BASE_URI)
                .queryParam(Deeplink.QueryParams.PAGE, DeeplinkPage.PRODUCT.getValue())
                .queryParam(Deeplink.QueryParams.CONTENT_ID, contentId)
                .queryParamIfPresent(Deeplink.QueryParams.CAMPAIGN_ID, Optional.ofNullable(queryParams.get(Weblink.QueryParam.BOUTIQUE_ID)))
                .queryParamIfPresent(Deeplink.QueryParams.MERCHANT_ID, Optional.ofNullable(queryParams.get(Weblink.QueryParam.MERCHANT_ID)))
                .build();

        return deeplinkUri.toString();
    }

    /**
     * Returns true if the weblink is a product page weblink.
     * It means that second segment of the weblink contains "-p-".
     * For example: https://www.trendyol.com/casio/erkek-kol-saati-p-1925865
     */
    @Override
    public boolean isWeblinkApplicable(UriComponents weblinkUri) {
        var pathSegments = weblinkUri.getPathSegments();
        var applicableSegmentCount = 2;
        if (pathSegments.size() != applicableSegmentCount) {
            return false;
        }

        var lastSegmentIndex = 1;
        return VALID_PRODUCT_LINK_SEGMENT_PATTERN.matcher(pathSegments.get(lastSegmentIndex)).matches();
    }
}
