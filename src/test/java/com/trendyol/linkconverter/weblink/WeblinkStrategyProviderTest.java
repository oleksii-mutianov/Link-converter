package com.trendyol.linkconverter.weblink;

import com.trendyol.linkconverter.weblink.strategies.DefaultWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.ProductDetailsWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.SearchWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.WeblinkStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeblinkStrategyProviderTest {

    @Mock
    private DefaultWeblinkStrategy defaultWeblinkStrategy;

    @Mock
    private SearchWeblinkStrategy searchWeblinkStrategy;

    @Mock
    private ProductDetailsWeblinkStrategy productDetailsWeblinkStrategy;

    @Mock
    private List<WeblinkStrategy> weblinkStrategies;

    @InjectMocks
    private WeblinkStrategyProvider weblinkStrategyProvider;

    @BeforeEach
    void setUp() {
        when(weblinkStrategies.stream())
                .thenReturn(Stream.of(defaultWeblinkStrategy, searchWeblinkStrategy, productDetailsWeblinkStrategy));
    }

    @Test
    void shouldReturnApplicableStrategy() {
        // GIVEN
        var weblinkUri = UriComponentsBuilder.fromUriString("https://www.trendyol.com/sr?q=elbise").build();
        when(searchWeblinkStrategy.isWeblinkApplicable(weblinkUri)).thenReturn(true);

        // WHEN
        var weblinkStrategy = weblinkStrategyProvider.getWeblinkStrategy(weblinkUri);

        // THEN
        assertEquals(searchWeblinkStrategy, weblinkStrategy);
    }

    @Test
    void shouldReturnDefaultWeblinkStrategyIfNoStrategyApplicable() {
        // GIVEN
        var weblinkUri = UriComponentsBuilder.fromUriString("https://www.trendyol.com/Hesabim/Favoriler").build();

        // WHEN
        var weblinkStrategy = weblinkStrategyProvider.getWeblinkStrategy(weblinkUri);

        // THEN
        assertEquals(defaultWeblinkStrategy, weblinkStrategy);
    }

}