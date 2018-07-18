package com.bozdag.votingserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Vote {
    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    private Restaurant restaurant;

    @OneToOne
    private Voter voter;

    @JsonIgnore
    @ManyToOne
    private Election election;

    private Vote() { } // JPA only

    public Long getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Voter getVoter() {
        return voter;
    }
}
