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

	@Bean
	CommandLineRunner init(RestaurantRespository restaurantRespository, VoterRepository voterRepository,
						   ElectionRepository electionRepository, VoteRepository voteRepository) {
	return args -> {
		Restaurant quickChina = restaurantRespository.save(new Restaurant("Quick China", "Chinese Food", "Tepe Prime Ankara"));
		Restaurant numnum = restaurantRespository.save(new Restaurant("Num Num", "Cafe", "Tepe Prime Ankara"));
		Restaurant ilForno = restaurantRespository.save(new Restaurant("Il Forno", "Pizza", "Bilkent Ankara"));

		Voter v1 = voterRepository.save(new Voter("sbozdag", "12345", "selcuk", "bozdag"));
		Voter v2 = voterRepository.save(new Voter("abozdag", "abcdef", "arte bilge", "bozdag"));
		Voter v3 = voterRepository.save(new Voter("zbozdag", "1a2b3c", "zeynep", "bozdag"));

		Set<Restaurant> candids = new HashSet<>();
		candids.add(quickChina);
		candids.add(numnum);
		candids.add(ilForno);

		Election e1 = electionRepository.save(new Election("Haftasonu", "Haftasonu ogle yemegi",
										LocalDateTime.of(2018, 7, 27, 9, 0),
										LocalDateTime.of(2018, 7, 27, 17, 0),
										candids));

		Vote vote1 = voteRepository.save(new Vote(quickChina, v1));
		Vote vote2 = voteRepository.save(new Vote(numnum, v2));
		Vote vote3 = voteRepository.save(new Vote(ilForno, v3));
	};
	}
}
