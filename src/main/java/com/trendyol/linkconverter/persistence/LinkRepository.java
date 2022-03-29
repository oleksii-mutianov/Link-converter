package com.trendyol.linkconverter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for LinkEntity persistence.
 */
public interface LinkRepository extends JpaRepository<LinkEntity, String> {
    /**
     * Searches for a LinkEntity by its request link.
     *
     * @param requestLink the request link
     * @return found LinkEntity or empty optional
     */
    Optional<LinkEntity> findByRequestLink(String requestLink);
}
