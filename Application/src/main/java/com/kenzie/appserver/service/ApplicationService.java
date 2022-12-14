package com.kenzie.appserver.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApplicationService {
    private ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public List<Application> createApplication() {
        List<User> users = new ArrayList<>();

        Iterable<ApplicationRecord> applicationRecordIterable = applicationRepository.findAll();
        for (ApplicationRecord record : applicationRecordIterable) {
            //a check to see if the application is open
            if (record.getOpenApplications()) {
                record.add(new User(UUID.fromString(record.getApplicationId()),
                        record.getTimeStamp(),
                        record.getResume(),
                        record.getReferences(),
                        record.getWorkHistory(),
                        record.getJobCriteria(),
                        record.getCriteria();
            }
        }

        return users;
    }

    public Application getAllApplications(String applicationId) {
        //potentially implement cache and cache check later with cache if found then cache logic...
        Application applicationFromRepository = applicationRepository.findById(applicationId)
                .map(applicationMatch -> new Application(UUID.fromString(applicationMatch.getApplicationId()),
                        applicationMatch.getTimeStamp(),
                        applicationMatch.getResume(),
                        applicationMatch.getReferences(),
                        applicationMatch.getWorkHistory(),
                        applicationMatch.geJobCriterua(),
                        applicationMatch.getCriteria();
                .orElse(null);
        return applicationsFromRepository;
    }

    public Application getApplication(Application applicationsToAdd) {
        ApplicationRecord applicationRecord = new ApplicationRecord();
        applicationRecord.setApplicationId(applicationsToAdd.getApplicationId().toString());
        applicationRecord.setTimeStamp(applicationToAdd.getTimeStamp());
        applicationRecord.setResume(applicationToAdd.getResume());
        applicationRecord.setReferences(applicationToAdd.getReferences());
        applicationRecord.setWorkHistory(applicationToAdd.getWorkHistory());
        applicationRecord.setJobCriteria(applicationToAdd.getJobCriteria());
        applicationRecord.setCriteria(applicationToAdd.getCriteria());
        return applicationsToAdd;
    }

    public void updateApplication(Application applicationToUpdate) {
        if (ApplicationRepository.existsById(applicationToUpdate.getApplicationId().toString())) {
            ApplicationRecord applicationRecord = new ApplicationRecord();
            applicationRecord.setApplicationId(applicationToUpdate.getApplicationId().toString());
            applicationRecord.setTimeStamp(applicationToUpdate.getTimeStamp());
            applicationRecord.setResume(applicationToAdd.getResume());
            applicationRecord.setReferences(applicationToAdd.getReferences());
            applicationRecord.setWorkHistory(applicationToAdd.getWorkHistory());
            applicationRecord.setJobCriteria(applicationToAdd.getJobCriteria());
            applicationRecord.setCriteria(applicationToAdd.getCriteria());
        }
    }

    public void deleteApplication(String applicationId) {
        applicationRepository.deleteById(applicationId);
    }
}

}
