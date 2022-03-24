package com.trendyol.linkconverter.deeplink.persistence;

import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface DeeplinkRepository extends KeyValueRepository<DeeplinkEntity, String> {
}
