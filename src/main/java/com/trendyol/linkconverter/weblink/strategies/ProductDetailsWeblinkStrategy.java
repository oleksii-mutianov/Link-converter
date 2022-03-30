package com.trendyol.linkconverter.weblink.strategies;

import com.google.common.base.CharMatcher;
import com.trendyol.linkconverter.config.QueryMappings;
import com.trendyol.linkconverter.constants.Deeplink;
import com.trendyol.linkconverter.constants.Weblink;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Strategy for product weblinks.
 */
@Component
public class ProductDetailsWeblinkStrategy extends AbstractWeblinkStrategyPersistableTemplate {

    private static final String ANY_SYMBOLS = ".+";
    private static final String ANY_DIGITS = "[0-9]+";
    private static final Pattern VALID_PRODUCT_LINK_SEGMENT_PATTERN =
            Pattern.compile(ANY_SYMBOLS + Weblink.PathSegments.PRODUCT_DELIMITER + ANY_DIGITS);

    private final QueryMappings queryMappings;

    public ProductDetailsWeblinkStrategy(LinkRepository linkRepository, QueryMappings queryMappings) {
        super(linkRepository);
        this.queryMappings = queryMappings;
    }

    /**
     * Converts product page weblink to deeplink
     */
    @Override
    protected String createNewResponseLink(UriComponents weblinkUri) {
        var pathSegments = weblinkUri.getPathSegments();
        var lastSegment = pathSegments.get(pathSegments.size() - 1);
        var contentId = CharMatcher.inRange('0', '9').retainFrom(lastSegment);
        var queryParams = weblinkUri.getQueryParams();

        var deeplinkUriBuilder = UriComponentsBuilder.fromUriString(Deeplink.BASE_URI)
                .queryParam(Deeplink.QueryParams.PAGE, DeeplinkPage.PRODUCT.getValue())
                .queryParam(Deeplink.QueryParams.CONTENT_ID, contentId);

        applyOptionalParams(deeplinkUriBuilder, queryParams);

        return deeplinkUriBuilder.build().toString();
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

    /**
     * Applies optional parameters to the deeplink
     *
     * @param deeplinkUriBuilder weblink builder to add optional parameters
     * @param queryParams        query parameters to be added
     */
    private void applyOptionalParams(UriComponentsBuilder deeplinkUriBuilder, MultiValueMap<String, String> queryParams) {
        queryMappings.getOptionalParams()
                .forEach((weblinkParam, deeplinkParam) ->
                        deeplinkUriBuilder.queryParamIfPresent(deeplinkParam, Optional.ofNullable(queryParams.get(weblinkParam))));
    }
}
