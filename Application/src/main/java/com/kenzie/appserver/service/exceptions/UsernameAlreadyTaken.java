package com.kenzie.appserver.service.exceptions;

public class UsernameAlreadyTaken extends RuntimeException {

    public UsernameAlreadyTaken() {
        super();
    }

    public UsernameAlreadyTaken(String message) {
        super(message);
    }
}
