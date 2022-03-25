package com.trendyol.linkconverter.weblink.strategies;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class DefaultWeblinkStrategyTest {

    @InjectMocks
    private DefaultWeblinkStrategy defaultWeblinkStrategy;

    @DisplayName("Any weblink should not be applicable")
    @ParameterizedTest
    @MethodSource("isWeblinkApplicableMethod")
    void isWeblinkApplicable(String requestLink, boolean expected) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var isApplicable = defaultWeblinkStrategy.isWeblinkApplicable(requestUri);
        assertEquals(expected, isApplicable);
    }

    static Stream<Arguments> isWeblinkApplicableMethod() {
        return Stream.of(
                arguments("https://www.trendyol.com", false),
                arguments("https://www.trendyol.com/brand/name-p-1925865", false),
                arguments("https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892", false),
                arguments("https://www.trendyol.com/sr?q=elbise", false)
        );
    }

    @DisplayName("Should return default deeplink")
    @ParameterizedTest
    @MethodSource("getDeeplinkMethodSource")
    void getDeeplink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = defaultWeblinkStrategy.getDeeplink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

    static Stream<Arguments> getDeeplinkMethodSource() {
        return Stream.of(
                arguments("https://www.trendyol.com/Hesabim/Favoriler", "ty://?Page=Home"),
                arguments("https://www.trendyol.com/Hesabim/#/Siparislerim", "ty://?Page=Home"),
                arguments("https://www.trendyol.com", "ty://?Page=Home")
        );
    }

}