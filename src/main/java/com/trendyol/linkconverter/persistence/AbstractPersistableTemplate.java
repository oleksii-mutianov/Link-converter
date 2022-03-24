package com.trendyol.linkconverter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponents;

public abstract class AbstractPersistableTemplate {

    @Autowired
    private LinkRepository linkRepository;

    protected abstract String createNewResponseLink(UriComponents uriComponents);

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
