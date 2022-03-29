package com.trendyol.linkconverter.weblink;

import com.trendyol.linkconverter.weblink.strategies.DefaultWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.WeblinkStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;

import java.util.List;

/**
 * Class for providing weblink strategies.
 */
@Component
@RequiredArgsConstructor
public class WeblinkStrategyProvider {
    private final List<WeblinkStrategy> weblinkStrategies;
    private final DefaultWeblinkStrategy defaultWeblinkStrategy;

    /**
     * Get the appropriate strategy for the given weblink.
     */
    public WeblinkStrategy getWeblinkStrategy(UriComponents weblinkUri) {
        return weblinkStrategies.stream()
                .filter(strategy -> strategy.isWeblinkApplicable(weblinkUri))
                .findFirst()
                .orElse(defaultWeblinkStrategy);
    }
}
