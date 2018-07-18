package com.bozdag.votingserver.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Election {

    @GeneratedValue
    @Id
    private Long id;

    private String name;
    private String description;
    private LocalDate date;

    @OneToMany(mappedBy = "election")
    private Set<Vote> votes = new HashSet<>();

    private Election() {} // JPA only

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
