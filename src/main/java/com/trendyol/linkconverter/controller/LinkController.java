package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.dto.DeeplinkResponseDto;
import com.trendyol.linkconverter.dto.WeblinkRequestDto;
import com.trendyol.linkconverter.weblink.sevice.WeblinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/converter")
public class LinkController {

    private final WeblinkService weblinkService;

    @PostMapping("weblink-to-deeplink")
    public DeeplinkResponseDto convertToDeeplink(@RequestBody @Valid WeblinkRequestDto weblinkRequestDto) {
        return weblinkService.convertToDeeplink(weblinkRequestDto);
    }

}
