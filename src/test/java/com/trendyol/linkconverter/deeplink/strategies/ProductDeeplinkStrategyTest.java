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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDeeplinkStrategyTest {

    @Mock
    private LinkRepository linkRepository;

    @InjectMocks
    private ProductDeeplinkStrategy productDeeplinkStrategy;

    @Test
    void shouldReturnLinkFromDbIfFound() {
        // GIVEN
        var requestLink = "ty://?Page=Product&ContentId=1925865";
        var responseLink = "https://www.trendyol.com/brand/name-p-1925865";
        when(linkRepository.findByRequestLink(requestLink))
                .thenReturn(Optional.of(
                        new LinkEntity(requestLink, responseLink)
                ));

        // WHEN
        var actualResponseLink = productDeeplinkStrategy.getWeblink(UriComponentsBuilder.fromUriString(requestLink).build());

        // THEN
        assertEquals(responseLink, actualResponseLink);
        verify(linkRepository).findByRequestLink(requestLink);
    }

    @Test
    void shouldThrowExceptionWhenMultipleContentIdPresent() {
        var requestUri = UriComponentsBuilder.fromUriString("ty://?Page=Product&ContentId=1925865&ContentId=1925866").build();
        var exception = assertThrows(RuntimeException.class, () -> productDeeplinkStrategy.getWeblink(requestUri));
        assertEquals("Multiple 'ContentId' parameter not supported for deeplinks", exception.getMessage());
    }

    @DisplayName("Should return deeplink for product page")
    @ParameterizedTest
    @MethodSource("getWeblinkTestData")
    void getWeblink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = productDeeplinkStrategy.getWeblink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

    static Stream<Arguments> getWeblinkTestData() {
        return Stream.of(
                arguments(
                        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064",
                        "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064"
                ),
                arguments(
                        "ty://?Page=Product&ContentId=1925865",
                        "https://www.trendyol.com/brand/name-p-1925865"
                ),
                arguments(
                        "ty://?Page=Product&ContentId=1925865&CampaignId=439892",
                        "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892"
                ),
                arguments(
                        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064",
                        "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064"
                )
        );
    }
}