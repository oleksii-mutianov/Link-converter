package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.weblink.persistence.WeblinkEntity;
import com.trendyol.linkconverter.weblink.persistence.WeblinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponents;

@RequiredArgsConstructor
public abstract class AbstractWeblinkStrategyPersistableTemplate implements WeblinkStrategy {

    private final WeblinkRepository weblinkRepository;

    protected abstract String createNewDeeplink(UriComponents weblinkUri);

    @Override
    public String getDeeplink(UriComponents weblinkUri) {
        var weblink = weblinkUri.toString();
        var weblinkEntity = weblinkRepository.findById(weblink);
        if (weblinkEntity.isPresent()) {
            return weblinkEntity.get().getDeeplink();
        }

        var deeplink = createNewDeeplink(weblinkUri);
        weblinkRepository.save(new WeblinkEntity(weblink, deeplink));

        return deeplink;
    }
}
