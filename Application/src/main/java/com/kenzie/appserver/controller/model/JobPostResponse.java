package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobPostResponse {

    @JsonProperty("jobPostId")
    private String jobPostId;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("positionTitle")
    private String positionTitle;

    @JsonProperty("companyName")
    private String companyName;

    @JsonProperty("location")
    private String location;

    @JsonProperty("proposedSalary")
    private Integer proposedSalary;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isFullTime")
    private Boolean isFullTime;

    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getProposedSalary() {
        return proposedSalary;
    }

    public void setProposedSalary(Integer proposedSalary) {
        this.proposedSalary = proposedSalary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFullTime() {
        return isFullTime;
    }

    public void setFullTime(Boolean fullTime) {
        isFullTime = fullTime;
    }
}
