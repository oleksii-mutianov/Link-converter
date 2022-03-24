package com.trendyol.linkconverter.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponents;

public abstract class AbstractPersistableTemplate {

    @Autowired
    private LinkRepository linkRepository;

    protected abstract String createNewResponseLink(UriComponents uriComponents);

    protected String getResponseLink(UriComponents uriComponents) {
        var requestLink = uriComponents.toString();
        var linkEntity = linkRepository.findById(requestLink);
        if (linkEntity.isPresent()) {
            return linkEntity.get().getResponseLink();
        }

        var responseLink = createNewResponseLink(uriComponents);
        linkRepository.save(new LinkEntity(requestLink, responseLink));

        return responseLink;
    }
}
