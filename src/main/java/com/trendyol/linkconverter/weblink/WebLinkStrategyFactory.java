package com.trendyol.linkconverter.weblink;

import com.trendyol.linkconverter.weblink.strategies.DefaultWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.WebLinkStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebLinkStrategyFactory {
    private final List<WebLinkStrategy> webLinkStrategies;
    private final DefaultWeblinkStrategy defaultWeblinkStrategy;

    public WebLinkStrategy getWebLinkStrategy(UriComponents webLinkUri) {
        return webLinkStrategies.stream()
                .filter(strategy -> strategy.isWebLinkApplicable(webLinkUri))
                .findFirst()
                .orElse(defaultWeblinkStrategy);
    }
}
