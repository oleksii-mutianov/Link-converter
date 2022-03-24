package com.trendyol.linkconverter.deeplink.persistence;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash
public class DeeplinkEntity {
    @Id
    private final String deeplink;
    private final String weblink;
}
