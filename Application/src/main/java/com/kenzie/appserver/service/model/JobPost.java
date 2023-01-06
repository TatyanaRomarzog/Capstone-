package com.kenzie.appserver.service.model;

import java.util.UUID;

public class JobPost {
    private final String employerUsername;
    private final UUID jobPostId;
    private final String positionTitle;
    private final String companyName;
    private final String location;
    private final int proposedSalary;
    private final String description;
    private final Boolean isFullTime;

    public JobPost(String employerUsername, UUID jobPostId, String positionTitle, String companyName,
                   String location, int proposedSalary, String description, Boolean isFullTime) {
        this.employerUsername = employerUsername;
        this. jobPostId = jobPostId;
        this.positionTitle = positionTitle;
        this.companyName = companyName;
        this.location = location;
        this.proposedSalary = proposedSalary;
        this.description = description;
        this.isFullTime = isFullTime;
    }

    public String getEmployerUsername() {
        return employerUsername;
    }

    public UUID getJobPostId() {
        return jobPostId;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public int getProposedSalary() {
        return proposedSalary;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFullTime() {
        return isFullTime;
    }
}
