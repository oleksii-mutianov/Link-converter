package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.deeplink.service.DeeplinkService;
import com.trendyol.linkconverter.dto.DeeplinkRequestDto;
import com.trendyol.linkconverter.dto.DeeplinkResponseDto;
import com.trendyol.linkconverter.dto.WeblinkRequestDto;
import com.trendyol.linkconverter.dto.WeblinkResponseDto;
import com.trendyol.linkconverter.weblink.sevice.WeblinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Controller for links converting.
 */
@Tag(name = "Link Converter API")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/converter")
public class LinkController {

    private final WeblinkService weblinkService;
    private final DeeplinkService deeplinkService;

    /**
     * Convert weblink to deeplink
     *
     * @param weblinkRequestDto request with web link
     * @return response with deeplink
     */
    @Operation(summary = "Convert weblink to deeplink")
    @PostMapping("weblink-to-deeplink")
    public DeeplinkResponseDto convertToDeeplink(@RequestBody @Valid WeblinkRequestDto weblinkRequestDto) {
        return weblinkService.convertToDeeplink(weblinkRequestDto);
    }

    /**
     * Convert deeplink to weblink
     *
     * @param deeplinkRequestDto request with deeplink
     * @return response with weblink
     */
    @Operation(summary = "Convert deeplink to weblink")
    @PostMapping("deeplink-to-weblink")
    public WeblinkResponseDto convertToWeblink(@RequestBody @Valid DeeplinkRequestDto deeplinkRequestDto) {
        return deeplinkService.convertToWeblink(deeplinkRequestDto);
    }

}
