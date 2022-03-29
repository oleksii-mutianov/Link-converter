package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.persistence.AbstractPersistableTemplate;
import org.springframework.web.util.UriComponents;

/**
 * Abstract class that provides persistence for deeplink strategies.
 */
public abstract class AbstractDeeplinkStrategyPersistableTemplate extends AbstractPersistableTemplate implements DeeplinkStrategy {
    /**
     * Delegates to {@link #getResponseLink} that can get weblink from database or save created one.
     */
    @Override
    public String getWeblink(UriComponents deeplinkUri) {
        return getResponseLink(deeplinkUri);
    }
}
