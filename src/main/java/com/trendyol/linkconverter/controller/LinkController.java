package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.DeepLinkResponseDto;
import com.trendyol.linkconverter.dto.WebLinkRequestDto;
import com.trendyol.linkconverter.weblink.strategies.DefaultWeblinkStrategy;
import com.trendyol.linkconverter.weblink.strategies.WebLinkStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/link")
public class LinkController {

    /*

    https://www.trendyol.com/sr?q=%C3%BCt%C3%BC
    ty://?Page=Search&Query=%C3%BCt%C3%BC

    https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064
    ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064

    */

    private final List<WebLinkStrategy> webLinkStrategies;
    private final DefaultWeblinkStrategy defaultWeblinkStrategy;

    @PostMapping
    public DeepLinkResponseDto convertToDeepLink(@RequestBody WebLinkRequestDto webLinkRequestDto) {

        var webLinkUri = UriComponentsBuilder.fromUriString(webLinkRequestDto.webLink()).build();

        var webLinkStrategy = webLinkStrategies.stream()
                .filter(strategy -> strategy.isWebLinkApplicable().test(webLinkUri))
                .findFirst()
                .orElse(defaultWeblinkStrategy);

        var deepLink = webLinkStrategy.createDeepLink(webLinkUri);

        return new DeepLinkResponseDto(deepLink);
    }
}
