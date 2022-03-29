package com.trendyol.linkconverter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for LinkEntity persistence.
 */
public interface LinkRepository extends JpaRepository<LinkEntity, String> {
    Optional<LinkEntity> findByRequestLink(String requestLink);
}
