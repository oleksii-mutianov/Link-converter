package com.trendyol.linkconverter.deeplink.service;

import com.trendyol.linkconverter.deeplink.DeepLinkStrategyFactory;
import com.trendyol.linkconverter.dto.DeeplinkRequestDto;
import com.trendyol.linkconverter.dto.WeblinkResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class DeeplinkService {

    private final DeepLinkStrategyFactory deepLinkStrategyFactory;

    public WeblinkResponseDto convertToWeblink(DeeplinkRequestDto deeplinkRequestDto) {
        var deeplinkUri = UriComponentsBuilder.fromUriString(deeplinkRequestDto.deeplink()).build();
        var deeplinkStrategy = deepLinkStrategyFactory.getDeeplinkStrategy(deeplinkUri);
        var weblink = deeplinkStrategy.getWeblink(deeplinkUri);
        return new WeblinkResponseDto(weblink);
    }
}
