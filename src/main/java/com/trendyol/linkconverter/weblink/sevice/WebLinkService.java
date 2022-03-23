package com.trendyol.linkconverter.weblink.sevice;

import com.trendyol.linkconverter.dto.DeepLinkResponseDto;
import com.trendyol.linkconverter.dto.WebLinkRequestDto;
import com.trendyol.linkconverter.weblink.WebLinkStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WebLinkService {

    private final WebLinkStrategyFactory webLinkStrategyFactory;

    public DeepLinkResponseDto convertToDeepLink(WebLinkRequestDto webLinkRequestDto) {
        var webLinkUri = UriComponentsBuilder.fromUriString(webLinkRequestDto.webLink()).build();

        var deepLink = webLinkStrategyFactory
                .getWebLinkStrategy(webLinkUri)
                .createDeepLink(webLinkUri);

        return new DeepLinkResponseDto(deepLink);
    }

}
