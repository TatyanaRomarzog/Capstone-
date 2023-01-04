package com.kenzie.appserver.service.model;

public class User {
    private final String username;
    private final String password;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String phoneNumber;
    private final String primaryEmail;

    public User(String username, String password, String firstName, String middleName, String lastName,
                String phoneNumber, String primaryEmail) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.primaryEmail = primaryEmail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }
}
