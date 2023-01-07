package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class JobPostUpdateRequest {

    @NotEmpty
    @JsonProperty("positionTitle")
    private String positionTitle;

    @NotEmpty
    @JsonProperty("companyName")
    private String companyName;

    @NotEmpty
    @JsonProperty("location")
    private String location;

    @NotEmpty
    @JsonProperty("proposedSalary")
    private Integer proposedSalary;

    @NotEmpty
    @JsonProperty("description")
    private String description;

    @NotEmpty
    @JsonProperty("isFullTime")
    private Boolean isFullTime;

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
