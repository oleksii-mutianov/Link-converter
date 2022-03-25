package com.trendyol.linkconverter.weblink.strategies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DefaultWeblinkStrategyTest {

    @InjectMocks
    private DefaultWeblinkStrategy defaultWeblinkStrategy;

    @DisplayName("Any weblink should not be applicable")
    @ParameterizedTest
    @CsvSource({
            "https://www.trendyol.com, false",
            "https://www.trendyol.com/brand/name-p-1925865, false",
            "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892, false",
            "https://www.trendyol.com/sr?q=elbise, false"
    })
    void isWeblinkApplicable(String requestLink, boolean expected) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var isApplicable = defaultWeblinkStrategy.isWeblinkApplicable(requestUri);
        assertEquals(expected, isApplicable);
    }

    @DisplayName("Should return default deeplink")
    @ParameterizedTest
    @CsvSource({
            "https://www.trendyol.com/Hesabim/Favoriler, ty://?Page=Home",
            "https://www.trendyol.com/Hesabim/#/Siparislerim, ty://?Page=Home",
            "https://www.trendyol.com,ty://?Page=Home"
    })
    void getDeeplink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = defaultWeblinkStrategy.getDeeplink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

}