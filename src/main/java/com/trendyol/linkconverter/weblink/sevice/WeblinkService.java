package com.trendyol.linkconverter.weblink.sevice;

import com.trendyol.linkconverter.constants.CacheNames;
import com.trendyol.linkconverter.dto.DeeplinkResponseDto;
import com.trendyol.linkconverter.dto.WeblinkRequestDto;
import com.trendyol.linkconverter.weblink.WeblinkStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WeblinkService {

    private final WeblinkStrategyFactory weblinkStrategyFactory;

    /**
     * Convert weblink to deeplink
     */
    @Cacheable(CacheNames.WEBLINK)
    public DeeplinkResponseDto convertToDeeplink(WeblinkRequestDto weblinkRequestDto) {
        var weblinkUri = UriComponentsBuilder.fromUriString(weblinkRequestDto.weblink()).build();
        var weblinkStrategy = weblinkStrategyFactory.getWeblinkStrategy(weblinkUri);
        var deeplink = weblinkStrategy.getDeeplink(weblinkUri);
        return new DeeplinkResponseDto(deeplink);
    }

}
