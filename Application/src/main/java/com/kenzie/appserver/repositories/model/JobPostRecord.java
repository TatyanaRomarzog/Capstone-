package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "JobPost")
public class JobPostRecord {
    private String employerUsername;
    private String jobPostId;
    private String timestamp;
    private String positionTitle;
    private String companyName;
    private String location;
    private Integer proposedSalary;
    private String description;
    private Boolean isFullTime;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "employerUsername", attributeName = "employerUsername")
    public String getEmployerUsername() {
        return employerUsername;
    }

    public void setEmployerUsername(String employerUsername) {
        this.employerUsername = employerUsername;
    }

    @DynamoDBHashKey(attributeName = "jobPostId")
    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    @DynamoDBAttribute(attributeName = "timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "positionTitle", attributeName = "positionTitle")
    public String getPositionTitle() {
        return positionTitle;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    @DynamoDBAttribute(attributeName = "companyName")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @DynamoDBAttribute(attributeName = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @DynamoDBAttribute(attributeName = "proposedSalary")
    public Integer getProposedSalary() {
        return proposedSalary;
    }

    public void setProposedSalary(Integer proposedSalary) {
        this.proposedSalary = proposedSalary;
    }

    @DynamoDBAttribute(attributeName = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DynamoDBAttribute(attributeName = "isFullTime")
    public Boolean getFullTime() {
        return isFullTime;
    }

    public void setFullTime(Boolean fullTime) {
        isFullTime = fullTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobPostRecord that = (JobPostRecord) o;
        return Objects.equals(employerUsername, that.employerUsername) && Objects.equals(jobPostId, that.jobPostId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employerUsername, jobPostId);
    }
}
