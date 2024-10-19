package com.shecodeafrica.hackathon.NippyResponse.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CONTACT_CENTRE")
@Data
public class ContactCentreEntity {
    @Id
    @GeneratedValue(generator = "CONTACT_CENTRE_SEQ")
    @SequenceGenerator(name = "CONTACT_CENTRE_SEQ", sequenceName = "CONTACT_CENTRE_SEQ", allocationSize = 1)
    private long id;

    @Column(name = "CATEGORY", length = 100)
    private String  category;

    @Column(name = "NAME", unique = true)
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "LGA")
    private String lga;

    @Column(name = "STATE")
    private String state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATE_CREATED")
    private Date dateCreated= new Date();

    @Column(name = "DATE_UPDATED")
    private Date dateUpdated;
}
