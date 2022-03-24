package com.trendyol.linkconverter.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.redis.core.index.Indexed;

@Data
@Document
public class LinkEntity {
    @Id
    private String id;
    @Indexed
    private final String requestLink;
    private final String responseLink;
}
