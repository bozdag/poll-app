package com.bozdag.votingserver.controller;

import com.bozdag.votingserver.domain.Restaurant;
import com.bozdag.votingserver.exception.RestaurantNotFoundException;
import com.bozdag.votingserver.repository.RestaurantRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/restaurants")
public class RestaurantRestController {

    private final RestaurantRespository restaurantRespository;

    @Autowired
    RestaurantRestController(RestaurantRespository restaurantRespository) {
        this.restaurantRespository = restaurantRespository;
    }

    @GetMapping
    Collection<Restaurant> getAll() {
        return restaurantRespository.findAll();
    }

    @GetMapping("/{restaurantId}")
    Restaurant getOne(@PathVariable Long restaurantId) {
        return restaurantRespository.findById(restaurantId)
                .orElseThrow( () -> new RestaurantNotFoundException(restaurantId) );
    }

    @PostMapping
    ResponseEntity create(@RequestBody Restaurant input) {
        Restaurant result = restaurantRespository.save(new Restaurant(  input.getName(),
                                                                        input.getDescription(), input.getAddress()));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(result.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
