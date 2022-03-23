package com.trendyol.linkconverter.weblink.persistence;

import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface WeblinkRepository extends KeyValueRepository<WeblinkEntity, String> {
}
