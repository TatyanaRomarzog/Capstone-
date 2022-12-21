package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "Application")
public class ApplicationRecord {
    private String userId;
    private String applicationId;
    private String timestamp;
    private String resumeAsJson;
    private List<String> workHistory;
    private List<String> references;
    private String criteriaAsJson;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "userId", attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBHashKey(attributeName = "applicationId")
    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @DynamoDBAttribute(attributeName = "timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @DynamoDBAttribute(attributeName = "resume")
    public String getResumeAsJson() {
        return resumeAsJson;
    }

    public void setResumeAsJson(String resumeAsJson) {
        this.resumeAsJson = resumeAsJson;
    }

    @DynamoDBAttribute(attributeName = "workHistory")
    public List<String> getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(List<String> workHistory) {
        this.workHistory = workHistory;
    }

    @DynamoDBAttribute(attributeName = "references")
    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = references;
    }

    @DynamoDBAttribute(attributeName = "jobCriteria")
    public String getCriteriaAsJson() {
        return criteriaAsJson;
    }

    public void setCriteriaAsJson(String criteriaAsJson) {
        this.criteriaAsJson = criteriaAsJson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationRecord that = (ApplicationRecord) o;
        return Objects.equals(userId, that.userId) && Objects.equals(applicationId, that.applicationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, applicationId);
    }
}
