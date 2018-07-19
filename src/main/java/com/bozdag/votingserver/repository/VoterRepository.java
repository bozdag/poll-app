package com.bozdag.votingserver.repository;

import com.bozdag.votingserver.domain.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoterRepository extends JpaRepository<Voter, Long>{
    Optional<Voter> findByUsername(String username);
}
