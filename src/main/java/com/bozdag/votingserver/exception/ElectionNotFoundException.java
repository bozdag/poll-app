package com.bozdag.votingserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElectionNotFoundException extends RuntimeException {
    public ElectionNotFoundException(Long id) {
        super("Could not find election id: " + id);
    }
}
