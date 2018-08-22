package com.bozdag.votingserver.controller;

import com.bozdag.votingserver.domain.Election;
import com.bozdag.votingserver.exception.ElectionNotFoundException;
import com.bozdag.votingserver.repository.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/elections")
public class ElectionRestController {

    private final ElectionRepository electionRepository;

    @Autowired
    ElectionRestController(ElectionRepository repository) {
        electionRepository = repository;
    }

    @GetMapping
    public Collection<Election> getAll() {
        return electionRepository.findAll();
    }

    @GetMapping("/{electionId}")
    public Election getOne(@PathVariable Long id) {
        return electionRepository.findById(id)
                .orElseThrow(() -> new ElectionNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Election election) {
        Election result =
                electionRepository.save(new Election(election.getName(),
                        election.getDescription(),
                        election.getVotingStartsAt(),
                        election.getVotingEndsAt(),
                        election.getCandidates()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
