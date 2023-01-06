package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationResponse {

    @JsonProperty("applicationId")
    private String applicationId;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("resume")
    private ResumeResponse resume;

    @JsonProperty("workHistory")
    private List<String> workHistory;

    @JsonProperty("references")
    private List<String> references;

    @JsonProperty("jobCriteria")
    private CriteriaResponse jobCriteria;

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ResumeResponse getResume() {
        return resume;
    }

    public void setResume(ResumeResponse resume) {
        this.resume = resume;
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

    public CriteriaResponse getJobCriteria() {
        return jobCriteria;
    }

    public void setJobCriteria(CriteriaResponse jobCriteria) {
        this.jobCriteria = jobCriteria;
    }
}
