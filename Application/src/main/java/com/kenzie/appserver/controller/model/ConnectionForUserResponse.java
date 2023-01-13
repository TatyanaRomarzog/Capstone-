package com.kenzie.appserver.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConnectionForUserResponse {

    @JsonProperty("connectionId")
    private String connectionId;

    @JsonProperty("applicationId")
    private String applicationId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("jobPost")
    private JobPostResponse jobPost;

    public String getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JobPostResponse getJobPost() {
        return jobPost;
    }

    public void setJobPost(JobPostResponse jobPost) {
        this.jobPost = jobPost;
    }
}
