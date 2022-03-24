package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.deeplink.persistence.DeeplinkEntity;
import com.trendyol.linkconverter.deeplink.persistence.DeeplinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.util.UriComponents;

@RequiredArgsConstructor
public abstract class AbstractDeeplinkStrategyPersistableTemplate implements DeeplinkStrategy {

    private final DeeplinkRepository deeplinkRepository;

    protected abstract String createNewWeblink(UriComponents deeplinkUri);

    @Override
    public String getWeblink(UriComponents deeplinkUri) {
        var deeplink = deeplinkUri.toString();
        var deeplinkEntity = deeplinkRepository.findById(deeplink);
        if (deeplinkEntity.isPresent()) {
            return deeplinkEntity.get().getDeeplink();
        }

        var weblink = createNewWeblink(deeplinkUri);
        deeplinkRepository.save(new DeeplinkEntity(deeplink, weblink));

        return weblink;
    }
}
