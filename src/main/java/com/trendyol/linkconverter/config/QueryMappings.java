package com.trendyol.linkconverter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Holder for optional query params mappings from property file.
 */
@Data
@Component
@ConfigurationProperties("query-mappings")
public class QueryMappings {

    /**
     * Optional query params mappings
     */
    private Map<String, String> optionalParams;

}
