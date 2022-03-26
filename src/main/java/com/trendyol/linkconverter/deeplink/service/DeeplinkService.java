package com.trendyol.linkconverter.deeplink.service;

import com.trendyol.linkconverter.constants.CacheNames;
import com.trendyol.linkconverter.deeplink.DeeplinkStrategyFactory;
import com.trendyol.linkconverter.dto.DeeplinkRequestDto;
import com.trendyol.linkconverter.dto.WeblinkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class DeeplinkService {

    private final DeeplinkStrategyFactory deeplinkStrategyFactory;

    /**
     * Convert deeplink to weblink
     */
    @Cacheable(CacheNames.DEEPLINK)
    public WeblinkResponseDto convertToWeblink(DeeplinkRequestDto deeplinkRequestDto) {
        var deeplinkUri = UriComponentsBuilder.fromUriString(deeplinkRequestDto.deeplink()).build();
        var deeplinkStrategy = deeplinkStrategyFactory.getDeeplinkStrategy(deeplinkUri);
        var weblink = deeplinkStrategy.getWeblink(deeplinkUri);
        return new WeblinkResponseDto(weblink);
    }
}
