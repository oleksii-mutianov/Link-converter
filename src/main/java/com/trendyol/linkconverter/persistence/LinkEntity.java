package com.trendyol.linkconverter.persistence;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Entity class for storing converted links
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(
        name = "links",
        indexes = @Index(columnList = "requestLink", unique = true)
)
public class LinkEntity {
    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    /**
     * Original link
     */
    private String requestLink;
    /**
     * Converted link
     */
    private String responseLink;

    public LinkEntity(String requestLink, String responseLink) {
        this.requestLink = requestLink;
        this.responseLink = responseLink;
    }
}
