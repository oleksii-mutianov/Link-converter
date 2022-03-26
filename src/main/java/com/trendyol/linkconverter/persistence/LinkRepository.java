package com.trendyol.linkconverter.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repository for LinkEntity persistence.
 */
public interface LinkRepository extends MongoRepository<LinkEntity, String> {
    Optional<LinkEntity> findByRequestLink(String requestLink);
}
