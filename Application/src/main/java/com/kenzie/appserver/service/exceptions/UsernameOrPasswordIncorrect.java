package com.kenzie.appserver.service.exceptions;

public class UsernameOrPasswordIncorrect extends RuntimeException{

    public UsernameOrPasswordIncorrect() {
        super();
    }

    public UsernameOrPasswordIncorrect(String message) {
        super(message);
    }
}
