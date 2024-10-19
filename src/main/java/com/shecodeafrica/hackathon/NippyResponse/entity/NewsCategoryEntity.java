package com.shecodeafrica.hackathon.NippyResponse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "NEWS_CATEGORY")
@Data
public class NewsCategoryEntity {
    @Id
    @GeneratedValue(generator = "NEWS_CATEGORY_SEQ")
    @SequenceGenerator(name = "NEWS_CATEGORY_SEQ", sequenceName = "NEWS_CATEGORY_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "NAME", length = 100, unique = true)
    private String  name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date dateCreated= new Date();

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_UPDATED")
    private Date dateUpdated;
}
