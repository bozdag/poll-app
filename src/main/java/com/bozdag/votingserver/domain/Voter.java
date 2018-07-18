package com.bozdag.votingserver.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Voter {

    @GeneratedValue
    @Id
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String name;
    private String surname;

    private Voter(){ } // JPA only

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
