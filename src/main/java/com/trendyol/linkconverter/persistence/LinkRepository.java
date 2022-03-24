package com.trendyol.linkconverter.persistence;

import org.springframework.data.keyvalue.repository.KeyValueRepository;

public interface LinkRepository extends KeyValueRepository<LinkEntity, String> {
}
