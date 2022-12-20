package com.kenzie.appserver.service.model;

import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Application {
    private final UUID userId;
    private final UUID applicationId;
    private final LocalDateTime timestamp;
    private final Resume resume;
    private final List<String> workHistory;
    private final List<String> references;
    private final Criteria jobCriteria;

    public Application(UUID userId, UUID applicationId, LocalDateTime timestamp, Resume resume, List<String> workHistory,
                       List<String> references, Criteria criteria) {
        this.userId = userId;
        this.applicationId = applicationId;
        this.timestamp = timestamp;
        this.resume = resume;
        this.workHistory = new ArrayList<>(workHistory);
        this.references = new ArrayList<>(references);
        this.jobCriteria = criteria;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getApplicationId() {
        return applicationId;
    }

    public LocalDateTime getTimestamp() {
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
