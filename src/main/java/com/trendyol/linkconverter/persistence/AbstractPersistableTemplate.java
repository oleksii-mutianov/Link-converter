package com.trendyol.linkconverter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponents;

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

        return foundResponseLink.orElseGet(() -> {
            var newResponseLink = createNewResponseLink(uriComponents);
            linkRepository.save(new LinkEntity(requestLink, newResponseLink));
            return newResponseLink;
        });
    }

}
