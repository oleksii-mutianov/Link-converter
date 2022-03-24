package com.trendyol.linkconverter.weblink;

import com.trendyol.linkconverter.weblink.strategies.DefaultWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.WeblinkStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WeblinkStrategyFactory {
    private final List<WeblinkStrategy> weblinkStrategies;
    private final DefaultWeblinkStrategy defaultWeblinkStrategy;

    public WeblinkStrategy getWeblinkStrategy(UriComponents weblinkUri) {
        return weblinkStrategies.stream()
                .filter(strategy -> strategy.isWeblinkApplicable(weblinkUri))
                .findFirst()
                .orElse(defaultWeblinkStrategy);
    }
}
