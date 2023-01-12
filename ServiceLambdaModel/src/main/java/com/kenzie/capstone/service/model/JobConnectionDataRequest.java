package com.kenzie.capstone.service.model;

public class JobConnectionDataRequest {
    private String applicationId;
    private String jobPostId;
    private String employerUsername;
    private String applicantUsername;

    public JobConnectionDataRequest(String applicationId, String jobPostId,
                                    String employerUsername, String applicantUsername) {
        this.applicationId = applicationId;
        this.jobPostId = jobPostId;
        this.employerUsername = employerUsername;
        this.applicantUsername = applicantUsername;
    }

    public JobConnectionDataRequest() {

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

    @Override
    public String toString() {
        return "JobConnectionDataRequest{" +
                "applicationId='" + applicationId + '\'' +
                ", jobPostId='" + jobPostId + '\'' +
                ", employerUsername='" + employerUsername + '\'' +
                ", applicantUsername='" + applicantUsername + '\'' +
                '}';
    }
}
