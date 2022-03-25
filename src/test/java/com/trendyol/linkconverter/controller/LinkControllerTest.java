package com.trendyol.linkconverter.controller;

import com.trendyol.linkconverter.deeplink.service.DeeplinkService;
import com.trendyol.linkconverter.dto.DeeplinkRequestDto;
import com.trendyol.linkconverter.dto.DeeplinkResponseDto;
import com.trendyol.linkconverter.dto.WeblinkRequestDto;
import com.trendyol.linkconverter.dto.WeblinkResponseDto;
import com.trendyol.linkconverter.weblink.sevice.WeblinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LinkController.class)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class LinkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeblinkService weblinkService;
    @MockBean
    private DeeplinkService deeplinkService;

    @Test
    void convertToDeeplink() throws Exception {
        // GIVEN
        var requestBody = """
                {
                    "weblink": "https://www.trendyol.com/sr?q=tişört"
                }
                """;
        var expectedResponseBody = """
                {
                    "deeplink": "ty://?Page=Search&Query=tişört"
                }
                """;

        when(weblinkService.convertToDeeplink(new WeblinkRequestDto("https://www.trendyol.com/sr?q=tişört")))
                .thenReturn(new DeeplinkResponseDto("ty://?Page=Search&Query=tişört"));

        // WHEN
        var request = post("/v1/converter/weblink-to-deeplink")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
    }

    @Test
    void convertToWeblink() throws Exception {
        // GIVEN
        var requestBody = """
                {
                    "deeplink": "ty://?Page=Search&Query=tişört"
                }
                """;
        var expectedResponseBody = """
                {
                    "weblink": "https://www.trendyol.com/sr?q=tişört"
                }
                """;

        when(deeplinkService.convertToWeblink(new DeeplinkRequestDto("ty://?Page=Search&Query=tişört")))
                .thenReturn(new WeblinkResponseDto("https://www.trendyol.com/sr?q=tişört"));

        // WHEN
        var request = post("/v1/converter/deeplink-to-weblink")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody);

        // THEN
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
    }
}