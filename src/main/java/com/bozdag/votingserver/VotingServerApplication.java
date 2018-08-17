package com.bozdag.votingserver;

import com.bozdag.votingserver.domain.Election;
import com.bozdag.votingserver.domain.Restaurant;
import com.bozdag.votingserver.domain.Vote;
import com.bozdag.votingserver.domain.Voter;
import com.bozdag.votingserver.repository.ElectionRepository;
import com.bozdag.votingserver.repository.RestaurantRespository;
import com.bozdag.votingserver.repository.VoteRepository;
import com.bozdag.votingserver.repository.VoterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class VotingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingServerApplication.class, args);
	}
}
