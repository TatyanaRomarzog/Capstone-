package com.kenzie.capstone.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "LambdaJobConnectionTable")
public class JobConnectionRecord {

    private String connectionId;
    private String applicationId;
    private String jobPostId;
    private String employerUsername;
    private String applicantUsername;
    private String status;

    @DynamoDBHashKey(attributeName = "connectionId")
    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    @DynamoDBAttribute(attributeName = "applicationId")
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @DynamoDBAttribute(attributeName = "jobPostId")
    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "EmployerUsernameIndex", attributeName = "employerUsername")
    public String getEmployerUsername() {
        return employerUsername;
    }

    public void setEmployerUsername(String employerUsername) {
        this.employerUsername = employerUsername;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "ApplicantUsernameIndex", attributeName = "applicantUsername")
    public String getApplicantUsername() {
        return applicantUsername;
    }

    public void setApplicantUsername(String applicantUsername) {
        this.applicantUsername = applicantUsername;
    }

    @DynamoDBAttribute(attributeName = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobConnectionRecord that = (JobConnectionRecord) o;
        return Objects.equals(connectionId, that.connectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionId);
    }
}
