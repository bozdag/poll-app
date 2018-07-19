package com.bozdag.votingserver.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Election {

    @GeneratedValue
    @Id
    private Long id;

    private String name;
    private String description;
    private LocalDateTime votingStartsAt;
    private LocalDateTime votingEndsAt;

    @OneToMany(mappedBy = "election")
    private Set<Vote> votes = new HashSet<>();

    @OneToMany
    @JoinTable(name = "CANDIDATE",
            joinColumns = @JoinColumn(name = "ELECTION_ID"),
            inverseJoinColumns = @JoinColumn(name = "RESTAURANT_ID")
    )
    private Set<Restaurant> candidates = new HashSet<>();

    private Election() {} // JPA only

    public Election(String name, String description, LocalDateTime start, LocalDateTime finish,
                    Set<Restaurant> candidates) {
        this.name = name;
        this.description = description;
        this.votingStartsAt = start;
        this.votingEndsAt = finish;
        this.candidates = candidates;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getVotingStartsAt() {
        return votingStartsAt;
    }

    public LocalDateTime getVotingEndsAt() {
        return votingEndsAt;
    }

    public Set<Vote> getVotes() {
        return votes;
    }

    public Set<Restaurant> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Restaurant> candidates) {
        this.candidates = candidates;
    }
}
