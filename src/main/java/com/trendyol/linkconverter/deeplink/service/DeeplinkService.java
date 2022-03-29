package com.trendyol.linkconverter.deeplink.service;

import com.trendyol.linkconverter.constants.CacheNames;
import com.trendyol.linkconverter.deeplink.DeeplinkStrategyProvider;
import com.trendyol.linkconverter.dto.DeeplinkRequestDto;
import com.trendyol.linkconverter.dto.WeblinkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Service to process deeplinks.
 */
@Service
@RequiredArgsConstructor
public class DeeplinkService {

    private final DeeplinkStrategyProvider deeplinkStrategyProvider;

    /**
     * Convert deeplink to weblink
     *
     * @param deeplinkRequestDto request with deeplink
     * @return response with weblink
     */
    @Cacheable(CacheNames.DEEPLINK)
    public WeblinkResponseDto convertToWeblink(DeeplinkRequestDto deeplinkRequestDto) {
        var deeplinkUri = UriComponentsBuilder.fromUriString(deeplinkRequestDto.deeplink()).build();
        var deeplinkStrategy = deeplinkStrategyProvider.getDeeplinkStrategy(deeplinkUri);
        var weblink = deeplinkStrategy.getWeblink(deeplinkUri);
        return new WeblinkResponseDto(weblink);
    }
}
