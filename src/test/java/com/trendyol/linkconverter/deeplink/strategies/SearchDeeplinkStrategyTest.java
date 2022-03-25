package com.trendyol.linkconverter.deeplink.strategies;

import com.trendyol.linkconverter.persistence.LinkEntity;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchDeeplinkStrategyTest {

    @Mock
    private LinkRepository linkRepository;

    @InjectMocks
    private SearchDeeplinkStrategy searchDeeplinkStrategy;

    @Test
    void shouldReturnLinkFromDbIfFound() {
        // GIVEN
        var requestLink = "ty://?Page=Search&Query=elbise";
        var responseLink = "https://www.trendyol.com/sr?q=elbise";
        when(linkRepository.findByRequestLink(requestLink))
                .thenReturn(Optional.of(
                        new LinkEntity(requestLink, responseLink)
                ));

        // WHEN
        var actualResponseLink = searchDeeplinkStrategy.getWeblink(UriComponentsBuilder.fromUriString(requestLink).build());

        // THEN
        assertEquals(responseLink, actualResponseLink);
        verify(linkRepository).findByRequestLink(requestLink);
    }

    @DisplayName("Should return deeplink for search page")
    @ParameterizedTest
    @MethodSource("searchDeeplinkStrategyTestData")
    void getWeblink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = searchDeeplinkStrategy.getWeblink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

    static Stream<Arguments> searchDeeplinkStrategyTestData() {
        return Stream.of(
                arguments("ty://?Page=Search&Query=elbise", "https://www.trendyol.com/sr?q=elbise"),
                arguments("ty://?Page=Search&Query=%C3%BCt%C3%BC", "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC")
        );
    }
}