package com.trendyol.linkconverter.deeplink;

import com.trendyol.linkconverter.deeplink.strategies.DeeplinkStrategy;
import com.trendyol.linkconverter.deeplink.strategies.DefaultDeeplinkStrategy;
import com.trendyol.linkconverter.deeplink.strategies.ProductDeeplinkStrategy;
import com.trendyol.linkconverter.deeplink.strategies.SearchDeeplinkStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
class DeeplinkStrategyFactoryTest {

    @Spy
    private DefaultDeeplinkStrategy defaultDeeplinkStrategy;
    @Spy
    private SearchDeeplinkStrategy searchDeeplinkStrategy;
    @Spy
    private ProductDeeplinkStrategy productDeeplinkStrategy;

    private DeeplinkStrategyFactory deepLinkStrategyFactory;

    @BeforeEach
    void setUp() {
        deepLinkStrategyFactory = new DeeplinkStrategyFactory(List.of(
                defaultDeeplinkStrategy,
                searchDeeplinkStrategy,
                productDeeplinkStrategy
        ));
    }

    @DisplayName("Should return correct strategy")
    @ParameterizedTest
    @MethodSource("getStrategyTestData")
    void getStrategy(String link, Class<DeeplinkStrategy> expectedStrategyClass) {
        var uriComponents = UriComponentsBuilder.fromUriString(link).build();
        var actualStrategyClass = deepLinkStrategyFactory.getDeeplinkStrategy(uriComponents).getClass().getSuperclass();
        assertEquals(expectedStrategyClass, actualStrategyClass);
    }

    static Stream<Arguments> getStrategyTestData() {
        return Stream.of(
                arguments("ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", ProductDeeplinkStrategy.class),
                arguments("ty://?Page=Product&ContentId=1925865&MerchantId=105064", ProductDeeplinkStrategy.class),
                arguments("ty://?Page=Product&ContentId=1925865", ProductDeeplinkStrategy.class),
                arguments("ty://?Page=Search&Query=%C3%A7%C3%A7%C3%A7", SearchDeeplinkStrategy.class),
                arguments("ty://?Page=Search&Query=elbise", SearchDeeplinkStrategy.class),
                arguments("ty://?Page=Home", DefaultDeeplinkStrategy.class),
                arguments("ty://?Page=Favorites", DefaultDeeplinkStrategy.class),
                arguments("ty://?Page=Orders", DefaultDeeplinkStrategy.class)
        );
    }
}