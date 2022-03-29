package com.trendyol.linkconverter.persistence;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponents;

/**
 * Abstract class that provides persistence for links.
 */
@Slf4j
public abstract class AbstractPersistableTemplate {

    @Autowired
    private LinkRepository linkRepository;

    /**
     * Creates a new response link from request link.
     *
     * @param uriComponents request link
     * @return response link
     */
    protected abstract String createNewResponseLink(UriComponents uriComponents);

    /**
     * Tries to find the response link by request link in the database. If it is not found, it creates a new one and saves.
     *
     * @param uriComponents request link
     * @return response link
     */
    protected String getResponseLink(UriComponents uriComponents) {
        var requestLink = uriComponents.toString();
        var foundResponseLink = linkRepository.findByRequestLink(requestLink).map(LinkEntity::getResponseLink);

        foundResponseLink.ifPresent(responseLink -> log.info("Found response link: {}", responseLink));

        return foundResponseLink.orElseGet(() -> {
            log.info("Response link not found for request link: {}", requestLink);
            var newResponseLink = createNewResponseLink(uriComponents);
            log.info("Created new response link: {}", newResponseLink);
            linkRepository.save(new LinkEntity(requestLink, newResponseLink));
            return newResponseLink;
        });
    }

}
