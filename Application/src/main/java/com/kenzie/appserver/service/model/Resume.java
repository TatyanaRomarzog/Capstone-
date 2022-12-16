package com.kenzie.appserver.service.model;

import java.util.UUID;

public class Resume {

    // basic fields for a user to fill out
    public String firstName = "";
    public String lastName = "";
    public String city = "";
    public String state = "";
    public String zipCode = "";
    public String phoneNumber = "";
    public String emailAddress = "";
    public String objective = "";
    public String education = "";
    public String experience = "";
    public String skills = "";
    public int salaryDesire = 0;
    private UUID applicationId;


    public Resume(String firstName, String lastName, String city, String state, String zipCode, String phoneNumber,
                  String emailAddress, String objective, String education, String experience, String skills, int salaryDesire) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.objective = objective;
        this.education = education;
        this.experience = experience;
        this.skills = skills;
        this.salaryDesire = salaryDesire;
    }

    public Resume(String lastName, String state, UUID applicationId) {git
        this.lastName = lastName;
        this.state = state;
        this.applicationId = applicationId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public int getSalaryDesire() {
        return salaryDesire;
    }

    public void setSalaryDesire(int salaryDesire) {
        this.salaryDesire = salaryDesire;
    }

}




