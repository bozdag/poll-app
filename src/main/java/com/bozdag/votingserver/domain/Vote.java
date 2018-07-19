package com.bozdag.votingserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Vote {
    @GeneratedValue
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "RESTAURANT_ID")
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(name = "VOTER_ID")
    private Voter voter;

    @JsonIgnore
    @ManyToOne
    private Election election;

    private Vote() { } // JPA only

    public Vote(Restaurant restaurant, Voter voter) {
        this.restaurant = restaurant;
        this.voter = voter;
    }

    public Long getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Voter getVoter() {
        return voter;
    }

    public Election getElection() {
        return election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}
