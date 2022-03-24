package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.persistence.AbstractPersistableTemplate;
import org.springframework.web.util.UriComponents;

public abstract class AbstractDeeplinkStrategyPersistableTemplate extends AbstractPersistableTemplate implements DeeplinkStrategy {
    @Override
    public String getWeblink(UriComponents deeplinkUri) {
        return getResponseLink(deeplinkUri);
    }
}
