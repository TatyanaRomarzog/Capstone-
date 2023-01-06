package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class EmployerCreateRequest {

    @NotEmpty
    @JsonProperty("username")
    private String username;

    @NotEmpty
    @JsonProperty("password")
    private String password;

    @NotEmpty
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("middleName")
    private String middleName;

    @NotEmpty
    @JsonProperty("lastName")
    private String lastName;

    @NotEmpty
    @JsonProperty("companyName")
    private String companyName;

    @NotEmpty
    @JsonProperty("companyPhoneNumber")
    private String companyPhoneNumber;

    @NotEmpty
    @JsonProperty("companyEmail")
    private String companyEmail;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }
}
