package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.weblink.persistence.WeblinkEntity;
import com.trendyol.linkconverter.weblink.persistence.WeblinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponents;

@RequiredArgsConstructor
public abstract class AbstractWeblinkStrategyPersistableTemplate implements WebLinkStrategy {

    private final WeblinkRepository weblinkRepository;

    protected abstract String createNewDeepLink(UriComponents webLinkUri);

    @Override
    public String getDeepLink(UriComponents webLinkUri) {
        var webLink = webLinkUri.toString();
        var weblinkEntity = weblinkRepository.findById(webLink);
        if (weblinkEntity.isPresent()) {
            return weblinkEntity.get().getDeepLink();
        }

        var deepLink = createNewDeepLink(webLinkUri);
        weblinkRepository.save(new WeblinkEntity(webLink, deepLink));

        return deepLink;
    }
}
