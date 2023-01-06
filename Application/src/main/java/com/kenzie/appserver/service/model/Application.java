package com.kenzie.appserver.service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Application {
    private final String username;
    private final UUID applicationId;
    private final String timestamp;
    private final Resume resume;
    private final List<String> workHistory;
    private final List<String> references;
    private final Criteria jobCriteria;

    public Application(String username, UUID applicationId, String timestamp, Resume resume, List<String> workHistory,
                       List<String> references, Criteria criteria) {
        this.username = username;
        this.applicationId = applicationId;
        this.timestamp = timestamp;
        this.resume = resume;
        this.workHistory = new ArrayList<>(workHistory);
        this.references = new ArrayList<>(references);
        this.jobCriteria = criteria;
    }

    public String getUsername() {
        return username;
    }

    public UUID getApplicationId() {
        return applicationId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public Resume getResume() {
        return resume;
    }

    public List<String> getWorkHistory() {
        return workHistory;
    }

    public List<String> getReferences() {
        return references;
    }

    public Criteria getJobCriteria() {
        return jobCriteria;
    }
}
