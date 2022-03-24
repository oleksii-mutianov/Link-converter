package com.trendyol.linkconverter.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class LinkEntity {
    @Id
    private final String requestLink;
    private final String responseLink;
}
