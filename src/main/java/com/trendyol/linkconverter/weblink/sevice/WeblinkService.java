package com.trendyol.linkconverter.weblink.sevice;

import com.trendyol.linkconverter.constants.CacheNames;
import com.trendyol.linkconverter.dto.DeeplinkResponseDto;
import com.trendyol.linkconverter.dto.WeblinkRequestDto;
import com.trendyol.linkconverter.weblink.WeblinkStrategyProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service to process weblinks
 */
@Service
@RequiredArgsConstructor
public class WeblinkService {

    private final WeblinkStrategyProvider weblinkStrategyProvider;

    /**
     * Convert weblink to deeplink
     *
     * @param weblinkRequestDto request with weblink
     * @return response with deeplink
     */
    @Cacheable(CacheNames.WEBLINK)
    public DeeplinkResponseDto convertToDeeplink(WeblinkRequestDto weblinkRequestDto) {
        var weblinkUri = UriComponentsBuilder.fromUriString(weblinkRequestDto.weblink()).build();
        var weblinkStrategy = weblinkStrategyProvider.getWeblinkStrategy(weblinkUri);
        var deeplink = weblinkStrategy.getDeeplink(weblinkUri);
        return new DeeplinkResponseDto(deeplink);
    }

}
