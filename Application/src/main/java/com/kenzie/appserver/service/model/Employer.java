package com.kenzie.appserver.service.model;

public class Employer {
    private final String username;
    private final String password;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String companyName;
    private final String companyPhoneNumber;
    private final String companyEmail;

    public Employer(String username, String password, String firstName, String middleName, String lastName,
                String companyName, String companyPhoneNumber, String companyEmail) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyEmail = companyEmail;
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

    public String getCompanyName() {
        return companyName;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }
}
