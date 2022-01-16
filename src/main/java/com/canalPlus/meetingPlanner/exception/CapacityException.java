package com.canalPlus.meetingPlanner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CapacityException extends RuntimeException {
    public CapacityException(String message) {
        super(message);
    }
}
