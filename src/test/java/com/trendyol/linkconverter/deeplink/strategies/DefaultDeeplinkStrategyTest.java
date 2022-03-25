package com.trendyol.linkconverter.deeplink.strategies;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DefaultDeeplinkStrategyTest {

    @InjectMocks
    private DefaultDeeplinkStrategy defaultDeeplinkStrategy;

    @ParameterizedTest
    @CsvSource({
            "ty://?Page=Favorites, https://www.trendyol.com",
            "ty://?Page=Orders, https://www.trendyol.com",
            "ty://?Page=Profile, https://www.trendyol.com",
            "ty://?Page=Home, https://www.trendyol.com"
    })
    void getWeblink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = defaultDeeplinkStrategy.getWeblink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

}