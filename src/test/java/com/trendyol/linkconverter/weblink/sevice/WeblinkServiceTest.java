package com.trendyol.linkconverter.weblink.sevice;

import com.trendyol.linkconverter.dto.DeeplinkResponseDto;
import com.trendyol.linkconverter.dto.WeblinkRequestDto;
import com.trendyol.linkconverter.weblink.WeblinkStrategyProvider;
import com.trendyol.linkconverter.weblink.strategies.SearchWeblinkStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeblinkServiceTest {

    @Mock
    private SearchWeblinkStrategy searchWeblinkStrategy;

    @Mock
    private WeblinkStrategyProvider weblinkStrategyProvider;

    @InjectMocks
    private WeblinkService weblinkService;

    @Test
    void convertToDeeplink() {
        // GIVEN
        var deeplink = "https://www.trendyol.com/sr?q=elbise";
        var weblink = "ty://?Page=Search&Query=elbise";
        var deeplinkUri = UriComponentsBuilder.fromUriString(deeplink).build();
        var expectedWeblinkResponseDto = new DeeplinkResponseDto(weblink);
        when(weblinkStrategyProvider.getWeblinkStrategy(deeplinkUri)).thenReturn(searchWeblinkStrategy);
        when(searchWeblinkStrategy.getDeeplink(deeplinkUri)).thenReturn(weblink);

        // WHEN
        var actualWeblinkResponseDto = weblinkService.convertToDeeplink(new WeblinkRequestDto(deeplink));

        // THEN
        assertEquals(expectedWeblinkResponseDto, actualWeblinkResponseDto);
    }
}