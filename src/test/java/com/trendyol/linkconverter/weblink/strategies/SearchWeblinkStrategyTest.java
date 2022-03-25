package com.trendyol.linkconverter.weblink.strategies;

import com.trendyol.linkconverter.persistence.LinkEntity;
import com.trendyol.linkconverter.persistence.LinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchWeblinkStrategyTest {

    @Mock
    private LinkRepository linkRepository;

    @InjectMocks
    private SearchWeblinkStrategy searchWeblinkStrategy;

    @Test
    void shouldReturnLinkFromDbIfFound() {
        // GIVEN
        var requestLink = "https://www.trendyol.com/sr?q=elbise";
        var responseLink = "ty://?Page=Search&Query=elbise";
        when(linkRepository.findByRequestLink(requestLink))
                .thenReturn(Optional.of(
                        new LinkEntity(requestLink, responseLink)
                ));

        // WHEN
        var actualResponseLink = searchWeblinkStrategy.getDeeplink(UriComponentsBuilder.fromUriString(requestLink).build());

        // THEN
        assertEquals(responseLink, actualResponseLink);
        verify(linkRepository).findByRequestLink(requestLink);
    }

    @Test
    void shouldSaveLinkToDbIfNotFound() {
        // GIVEN
        var requestLink = "https://www.trendyol.com/sr?q=elbise";
        var responseLink = "ty://?Page=Search&Query=elbise";
        when(linkRepository.findByRequestLink(requestLink)).thenReturn(Optional.empty());

        // WHEN
        searchWeblinkStrategy.getDeeplink(UriComponentsBuilder.fromUriString(requestLink).build());

        // THEN
        verify(linkRepository).findByRequestLink(requestLink);
        verify(linkRepository).save(new LinkEntity(requestLink, responseLink));
    }

    @ParameterizedTest
    @CsvSource({
            "https://www.trendyol.com/sr?q=elbise, ty://?Page=Search&Query=elbise",
            "https://www.trendyol.com/sr?q=tişört, ty://?Page=Search&Query=tişört",
            "https://www.trendyol.com/sr?q=ti%C5%9F%C3%B6rt, ty://?Page=Search&Query=ti%C5%9F%C3%B6rt",
            "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC, ty://?Page=Search&Query=%C3%BCt%C3%BC"
    })
    void getDeeplink(String requestLink, String expectedResponseLink) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var actualResponseLink = searchWeblinkStrategy.getDeeplink(requestUri);
        assertEquals(expectedResponseLink, actualResponseLink);
    }

    @ParameterizedTest
    @CsvSource({
            "https://www.trendyol.com/sr?q=elbise, true",
            "https://www.trendyol.com/sr?q=tişört, true",
            "https://www.trendyol.com/sr?q=ti%C5%9F%C3%B6rt, true",
            "https://www.trendyol.com/sr?q=%C3%BCt%C3%BC, true",
            "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064, false",
            "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865, false",
            "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892, false",
            "https://www.trendyol.com/Hesabim/Favoriler, false"

    })
    void isWeblinkApplicable(String requestLink, boolean expected) {
        var requestUri = UriComponentsBuilder.fromUriString(requestLink).build();
        var isApplicable = searchWeblinkStrategy.isWeblinkApplicable(requestUri);
        assertEquals(expected, isApplicable);
    }

}