package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.config.QueryMappings;
import com.trendyol.linkconverter.constants.DeeplinkConstants;
import com.trendyol.linkconverter.constants.ErrorMessage;
import com.trendyol.linkconverter.constants.WeblinkConstants;
import com.trendyol.linkconverter.deeplink.enums.DeeplinkPage;
import com.trendyol.linkconverter.exception.InvalidParameterException;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * Strategy for product deeplinks.
 */
@Component
public class ProductDeeplinkStrategy extends AbstractDeeplinkStrategyPersistableTemplate {

    private final QueryMappings queryMappings;

    public ProductDeeplinkStrategy(LinkRepository linkRepository, QueryMappings queryMappings) {
        super(linkRepository);
        this.queryMappings = queryMappings;
    }

    /**
     * Converts product page deeplink to weblink
     *
     * @param deeplinkUri deeplink to be converted
     * @return weblink
     */
    @Override
    protected String createNewResponseLink(UriComponents deeplinkUri) {
        var queryParams = deeplinkUri.getQueryParams();

        var applicableParamCount = 1;
        if (queryParams.get(DeeplinkConstants.QueryParams.CONTENT_ID).size() != applicableParamCount) {
            throw new InvalidParameterException(ErrorMessage.MULTIPLE_CONTENT_ID);
        }

        var weblinkUriBuilder = UriComponentsBuilder.fromUriString(WeblinkConstants.BASE_URI)
                .pathSegment(WeblinkConstants.PathSegments.BRAND, WeblinkConstants.PathSegments.NAME_P + queryParams.get(DeeplinkConstants.QueryParams.CONTENT_ID).get(0));

        applyOptionalParams(weblinkUriBuilder, queryParams);

        return weblinkUriBuilder.build().toString();
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

    /**
     * Applies optional parameters to the weblink
     *
     * @param weblinkUriBuilder weblink builder to add optional parameters
     * @param queryParams       query parameters to be added
     */
    private void applyOptionalParams(UriComponentsBuilder weblinkUriBuilder, MultiValueMap<String, String> queryParams) {
        queryMappings.getOptionalParams()
                .forEach((weblinkParam, deeplinkParam) ->
                        weblinkUriBuilder.queryParamIfPresent(weblinkParam, Optional.ofNullable(queryParams.get(deeplinkParam))));
    }
}
