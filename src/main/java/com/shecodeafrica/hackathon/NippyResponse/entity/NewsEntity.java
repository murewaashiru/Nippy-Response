package com.shecodeafrica.hackathon.NippyResponse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "NEWS")
@Data
public class NewsEntity {
    @Id
    @GeneratedValue(generator = "NEWS_SEQ")
    @SequenceGenerator(name = "NEWS_SEQ", sequenceName = "NEWS_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "CATEGORY", length = 100)
    private String  category;

    //@Lob
    @Column(name = "MESSAGE")
    //@Column(name = "MESSAGE", columnDefinition="CLOB")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date dateCreated= new Date();

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_UPDATED")
    private Date dateUpdated;
}
