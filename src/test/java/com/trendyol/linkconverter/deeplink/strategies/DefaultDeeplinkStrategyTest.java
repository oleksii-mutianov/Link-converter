package com.trendyol.linkconverter.deeplink.strategies;

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
class DefaultDeeplinkStrategyTest {

    @InjectMocks
    private DefaultDeeplinkStrategy defaultDeeplinkStrategy;

    @ParameterizedTest
    @MethodSource("getWeblinkTestData")
    void getWeblink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = defaultDeeplinkStrategy.getWeblink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

    static Stream<Arguments> getWeblinkTestData() {
        return Stream.of(
                arguments("ty://?Page=Favorites", "https://www.trendyol.com"),
                arguments("ty://?Page=Orders", "https://www.trendyol.com"),
                arguments("ty://?Page=Profile", "https://www.trendyol.com"),
                arguments("ty://?Page=Home", "https://www.trendyol.com")
        );
    }

}