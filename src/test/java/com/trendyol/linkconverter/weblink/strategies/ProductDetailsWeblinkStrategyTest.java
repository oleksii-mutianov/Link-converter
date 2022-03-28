package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.persistence.LinkEntity;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDetailsWeblinkStrategyTest {

    @Mock
    private LinkRepository linkRepository;

    @InjectMocks
    private ProductDetailsWeblinkStrategy productDetailsWeblinkStrategy;

    @Test
    void shouldReturnLinkFromDbIfFound() {
        // GIVEN
        var requestLink = "https://www.trendyol.com/brand/name-p-1925865";
        var responseLink = "ty://?Page=Product&ContentId=1925865";
        when(linkRepository.findByRequestLink(requestLink))
                .thenReturn(Optional.of(
                        new LinkEntity(requestLink, responseLink)
                ));

        // WHEN
        var actualResponseLink = productDetailsWeblinkStrategy.getDeeplink(UriComponentsBuilder.fromUriString(requestLink).build());

        // THEN
        assertEquals(responseLink, actualResponseLink);
        verify(linkRepository).findByRequestLink(requestLink);
        verify(linkRepository, times(0)).save(any());
    }

    @Test
    void shouldSaveLinkToDbIfNotFound() {
        // GIVEN
        var requestLink = "https://www.trendyol.com/brand/name-p-1925865";
        var responseLink = "ty://?Page=Product&ContentId=1925865";
        when(linkRepository.findByRequestLink(requestLink)).thenReturn(Optional.empty());

        // WHEN
        productDetailsWeblinkStrategy.getDeeplink(UriComponentsBuilder.fromUriString(requestLink).build());

        // THEN
        verify(linkRepository).findByRequestLink(requestLink);
        verify(linkRepository).save(new LinkEntity(requestLink, responseLink));
    }

    @DisplayName("Should return correct deeplink for product page")
    @ParameterizedTest
    @MethodSource("getDeeplinkMethodSource")
    void getDeeplink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = productDetailsWeblinkStrategy.getDeeplink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

    static Stream<Arguments> getDeeplinkMethodSource() {
        return Stream.of(
                arguments(
                        "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064",
                        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
                ),
                arguments(
                        "https://www.trendyol.com/brand/name-p-1925865",
                        "ty://?Page=Product&ContentId=1925865"
                ),
                arguments(
                        "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892",
                        "ty://?Page=Product&ContentId=1925865&CampaignId=439892"
                ),
                arguments(
                        "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892&merchantId=105064",
                        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
                )
        );
    }

    @ParameterizedTest
    @CsvSource({
            "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064, true",
            "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865, true",
            "https://www.trendyol.com/tişört/tişört-p-1925865, true",
            "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892, true",
            "https://www.trendyol.com/sr?q=elbise, false",
            "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC, false",
            "https://www.trendyol.com/Hesabim/Favoriler, false",
            "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865hr, false",
            "https://www.trendyol.com/casio/erkek-1925865, false",
            "https://www.trendyol.com/casio/-p-1925865, false",
    })
    void isWeblinkApplicable(String requestLink, boolean expected) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var isApplicable = productDetailsWeblinkStrategy.isWeblinkApplicable(requestUri);
        assertEquals(expected, isApplicable);
    }

}