package com.trendyol.linkconverter.deeplink.service;

import com.trendyol.linkconverter.deeplink.DeeplinkStrategyFactory;
import com.trendyol.linkconverter.deeplink.strategies.SearchDeeplinkStrategy;
import com.trendyol.linkconverter.dto.DeeplinkRequestDto;
import com.trendyol.linkconverter.dto.WeblinkResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeeplinkServiceTest {

    @Mock
    private SearchDeeplinkStrategy searchDeeplinkStrategy;

    @Mock
    private DeeplinkStrategyFactory deepLinkStrategyFactory;

    @InjectMocks
    private DeeplinkService deeplinkService;

    @Test
    void convertToWeblink() {
        // GIVEN
        var deeplink = "ty://?Page=Search&Query=elbise";
        var weblink = "https://www.trendyol.com/sr?q=elbise";
        var deeplinkUri = UriComponentsBuilder.fromUriString(deeplink).build();
        var expectedWeblinkResponseDto = new WeblinkResponseDto(weblink);
        when(deepLinkStrategyFactory.getDeeplinkStrategy(deeplinkUri)).thenReturn(searchDeeplinkStrategy);
        when(searchDeeplinkStrategy.getWeblink(deeplinkUri)).thenReturn(weblink);

        // WHEN
        var actualWeblinkResponseDto = deeplinkService.convertToWeblink(new DeeplinkRequestDto(deeplink));

        // THEN
        assertEquals(expectedWeblinkResponseDto, actualWeblinkResponseDto);
    }
}