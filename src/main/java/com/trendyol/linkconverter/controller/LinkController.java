package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.DeepLinkResponseDto;
import com.trendyol.linkconverter.dto.WebLinkRequestDto;
import com.trendyol.linkconverter.weblink.sevice.WebLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/link")
public class LinkController {

    private final WebLinkService webLinkService;

    /*

    https://www.trendyol.com/sr?q=%C3%BCt%C3%BC
    ty://?Page=Search&Query=%C3%BCt%C3%BC

    https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064
    ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064

    */
    @PostMapping
    public DeepLinkResponseDto convertToDeepLink(@RequestBody @Valid WebLinkRequestDto webLinkRequestDto) {
        return webLinkService.convertToDeepLink(webLinkRequestDto);
    }
}
