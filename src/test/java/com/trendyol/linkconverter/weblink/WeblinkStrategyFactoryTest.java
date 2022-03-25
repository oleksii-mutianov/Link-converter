package com.trendyol.linkconverter.weblink;

import com.trendyol.linkconverter.weblink.strategies.DefaultWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.ProductDetailsWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.SearchWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.WeblinkStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@ExtendWith(MockitoExtension.class)
class WeblinkStrategyFactoryTest {

    @Spy
    private DefaultWeblinkStrategy defaultWeblinkStrategy;

    @Spy
    private SearchWeblinkStrategy searchWeblinkStrategy;

    @Spy
    private ProductDetailsWeblinkStrategy productDetailsWeblinkStrategy;

    private WeblinkStrategyFactory weblinkStrategyFactory;

    @BeforeEach
    void setUp() {
        var weblinkStrategies = List.of(defaultWeblinkStrategy, searchWeblinkStrategy, productDetailsWeblinkStrategy);
        weblinkStrategyFactory = new WeblinkStrategyFactory(weblinkStrategies, defaultWeblinkStrategy);
    }

    @ParameterizedTest
    @MethodSource("getWeblinkStrategyMethodSource")
    void getWeblinkStrategy(String link, Class<? extends WeblinkStrategy> expectedStrategyClass) {
        var uriComponents = UriComponentsBuilder.fromUriString(link).build();
        var actualStrategyClass = weblinkStrategyFactory.getWeblinkStrategy(uriComponents).getClass().getSuperclass();
        assertEquals(actualStrategyClass, expectedStrategyClass);
    }

    static Stream<Arguments> getWeblinkStrategyMethodSource() {
        return Stream.of(
                arguments("https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064", ProductDetailsWeblinkStrategy.class),
                arguments("https://www.trendyol.com/casio/erkek-kol-saati-p-1925865", ProductDetailsWeblinkStrategy.class),
                arguments("https://www.trendyol.com/sr?q=elbise", SearchWeblinkStrategy.class),
                arguments("https://www.trendyol.com/sr?q=%C3%BCt%C3%BC", SearchWeblinkStrategy.class),
                arguments("https://www.trendyol.com/Hesabim/Favoriler", DefaultWeblinkStrategy.class),
                arguments("https://www.trendyol.com/Hesabim/#/Siparislerim", DefaultWeblinkStrategy.class)
        );
    }
}