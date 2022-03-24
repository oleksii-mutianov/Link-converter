package com.trendyol.linkconverter.weblink.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash
public class WeblinkEntity {
    @Id
    private String weblink;
    private String deeplink;
}
