package com.kenzie.capstone.service.model;

import java.util.Objects;

public class JobConnectionData {
    private String connectionId;
    private String applicationId;
    private String jobPostId;
    private String employerUsername;
    private String applicantUsername;
    private String status;

    public JobConnectionData(String connectionId, String applicationId, String jobPostId, String employerUsername,
                             String applicantUsername, String status) {
        this.connectionId = connectionId;
        this.applicationId = applicationId;
        this.jobPostId = jobPostId;
        this.employerUsername = employerUsername;
        this.applicantUsername = applicantUsername;
        this.status = status;
    }

    public JobConnectionData() {

    }

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

    public String getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(String jobPostId) {
        this.jobPostId = jobPostId;
    }

    public String getEmployerUsername() {
        return employerUsername;
    }

    public void setEmployerUsername(String employerUsername) {
        this.employerUsername = employerUsername;
    }

    public String getApplicantUsername() {
        return applicantUsername;
    }

    public void setApplicantUsername(String applicantUsername) {
        this.applicantUsername = applicantUsername;
    }

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
        JobConnectionData that = (JobConnectionData) o;
        return Objects.equals(connectionId, that.connectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectionId);
    }
}
