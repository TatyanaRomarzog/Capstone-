package com.kenzie.appserver.service.model;

public class ConnectionForUser {
    private final String connectionId;
    private final String applicationId;
    private final String status;
    private final JobPost jobPost;

    public ConnectionForUser(String connectionId, String applicationId, String status, JobPost jobPost) {
        this.connectionId = connectionId;
        this.applicationId = applicationId;
        this.status = status;
        this.jobPost = jobPost;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getStatus() {
        return status;
    }

    public JobPost getJobPost() {
        return jobPost;
    }
}
