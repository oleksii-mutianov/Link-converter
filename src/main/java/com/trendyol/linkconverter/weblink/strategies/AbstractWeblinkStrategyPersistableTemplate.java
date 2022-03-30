package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.persistence.AbstractPersistableTemplate;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.springframework.web.util.UriComponents;

/**
 * Abstract class that provides persistence for weblink strategies.
 */
public abstract class AbstractWeblinkStrategyPersistableTemplate extends AbstractPersistableTemplate implements WeblinkStrategy {
    protected AbstractWeblinkStrategyPersistableTemplate(LinkRepository linkRepository) {
        super(linkRepository);
    }

    /**
     * Delegates to {@link #getResponseLink} that can get deeplink from database or save created one.
     */
    @Override
    public String getDeeplink(UriComponents weblinkUri) {
        return getResponseLink(weblinkUri);
    }
}
