package com.bozdag.votingserver.repository;

import com.bozdag.votingserver.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findById(Long id);
}
