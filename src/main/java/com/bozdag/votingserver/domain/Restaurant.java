package com.bozdag.votingserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Restaurant {

    @GeneratedValue
    @Id
    private Long id;

    private String name;
    private String description;
    private String address;

    private Restaurant() {} // JPA Only

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
