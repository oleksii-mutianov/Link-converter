package com.trendyol.linkconverter.weblink.sevice;

import com.trendyol.linkconverter.dto.DeepLinkResponseDto;
import com.trendyol.linkconverter.dto.WebLinkRequestDto;
import com.trendyol.linkconverter.weblink.WebLinkStrategyFactory;
import com.trendyol.linkconverter.weblink.persistence.WeblinkEntity;
import com.trendyol.linkconverter.weblink.persistence.WeblinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class WebLinkService {

    private final WebLinkStrategyFactory webLinkStrategyFactory;
    private final WeblinkRepository weblinkRepository;

    public DeepLinkResponseDto convertToDeepLink(WebLinkRequestDto webLinkRequestDto) {
        var webLink = webLinkRequestDto.webLink();
        var webLinkUri = UriComponentsBuilder.fromUriString(webLink).build();

        var webLinkStrategy = webLinkStrategyFactory.getWebLinkStrategy(webLinkUri);

        if (webLinkStrategy.isCacheAvailable()) {
            var weblinkEntity = weblinkRepository.findById(webLink);
            if (weblinkEntity.isPresent()) {
                return new DeepLinkResponseDto(weblinkEntity.get().getDeepLink());
            }
        }

        var deepLink = webLinkStrategy.createDeepLink(webLinkUri);
        if (webLinkStrategy.isCacheAvailable()) {
            weblinkRepository.save(new WeblinkEntity(webLink, deepLink));
        }

        return new DeepLinkResponseDto(deepLink);
    }

}
