package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ApplicationCreateRequest {

    @NotEmpty
    @JsonProperty("firstName")
    private String firstName;

    @NotEmpty
    @JsonProperty("lastName")
    private String lastName;

    @NotEmpty
    @JsonProperty("homeAddress")
    private String homeAddress;

    @NotEmpty
    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @NotEmpty
    @JsonProperty("emailAddress")
    private String emailAddress;

    @NotEmpty
    @JsonProperty("objective")
    private String objective;

    @NotEmpty
    @JsonProperty("education")
    private String education;

    @NotEmpty
    @JsonProperty("experience")
    private String experience;

    @NotEmpty
    @JsonProperty("skills")
    private String skills;

    @JsonProperty("workHistory")
    private List<String> workHistory;

    @JsonProperty("references")
    private List<String> references;

    @NotEmpty
    @JsonProperty("positionTitle")
    private String positionTitle;

    @JsonProperty("locations")
    private List<String> locations;

    @JsonProperty("minimumSalary")
    private int minimumSalary;

    @JsonProperty("openJobsLimit")
    private int openJobsLimit;

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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
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

    public List<String> getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(List<String> workHistory) {
        this.workHistory = workHistory;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    public int getMinimumSalary() {
        return minimumSalary;
    }

    public void setMinimumSalary(int minimumSalary) {
        this.minimumSalary = minimumSalary;
    }

    public int getOpenJobsLimit() {
        return openJobsLimit;
    }

    public void setOpenJobsLimit(int openJobsLimit) {
        this.openJobsLimit = openJobsLimit;
    }
}
