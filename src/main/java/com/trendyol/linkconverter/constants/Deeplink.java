package com.trendyol.linkconverter.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Deeplink {

    public static final String BASE_URI = "ty://";
    public static final String DEFAULT_PAGE = "ty://?Page=Home";

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class QueryParams {
        public static final String PAGE = "Page";
        public static final String CONTENT_ID = "ContentId";
        public static final String CAMPAIGN_ID = "CampaignId";
        public static final String MERCHANT_ID = "MerchantId";
        public static final String QUERY = "Query";
    }
}
