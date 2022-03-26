package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.persistence.AbstractPersistableTemplate;
import org.springframework.web.util.UriComponents;

public abstract class AbstractWeblinkStrategyPersistableTemplate extends AbstractPersistableTemplate implements WeblinkStrategy {
    /**
     * Delegates to {@link #getResponseLink} that can get deeplink from database or save created one.
     */
    @Override
    public String getDeeplink(UriComponents weblinkUri) {
        return getResponseLink(weblinkUri);
    }
}
