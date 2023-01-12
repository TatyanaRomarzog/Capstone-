package com.kenzie.capstone.service.model;

public class JobConnectionResponse {
    private String connectionId;
    private String applicationId;
    private String jobPostId;
    private String employerUsername;
    private String applicantUsername;
    private String status;

    public JobConnectionResponse(String connectionId, String applicationId, String jobPostId, String employerUsername,
                             String applicantUsername, String status) {
        this.connectionId = connectionId;
        this.applicationId = applicationId;
        this.jobPostId = jobPostId;
        this.employerUsername = employerUsername;
        this.applicantUsername = applicantUsername;
        this.status = status;
    }

    public JobConnectionResponse() {

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
    public String toString() {
        return "JobConnectionResponse{" +
                "connectionId='" + connectionId + '\'' +
                ", applicationId='" + applicationId + '\'' +
                ", jobPostId='" + jobPostId + '\'' +
                ", employerUsername='" + employerUsername + '\'' +
                ", applicantUsername='" + applicantUsername + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
