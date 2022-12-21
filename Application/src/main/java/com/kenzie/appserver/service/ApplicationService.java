package com.kenzie.appserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kenzie.appserver.repositories.ApplicationRepository;
import com.kenzie.appserver.repositories.model.ApplicationRecord;
import com.kenzie.appserver.service.model.Application;
import com.kenzie.appserver.service.model.Criteria;
import com.kenzie.appserver.service.model.Resume;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ApplicationService {
    private ApplicationRepository applicationRepository;
    private ObjectMapper mapper;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository, ObjectMapper mapper) {
        this.applicationRepository = applicationRepository;
        this.mapper = mapper;
    }

    public Application createApplication(Application addApplication) {
        applicationRepository.save(recordFromApplication(addApplication));

        //TODO at this point, the add application was successfully saved into the database,
        // this is where our program will send our application to the lambda service to start applying for jobs
        // we will get here when we get here

        return addApplication;
    }

    public void updateApplication(Application applicationToUpdate) {
        if (applicationRepository.existsById(applicationToUpdate.getApplicationId().toString())) {
            applicationRepository.save(recordFromApplication(applicationToUpdate));
            //TODO, when someone updates an application, we need to resend the apply request to the lambda
            // this needs to be implemented after we get the lambda working
        }
    }

    public List<Application> getAllApplicationsForUser(String userId) {
        List<Application> applications = new ArrayList<>();

        List<ApplicationRecord> records = applicationRepository.findByUserId(userId);
        for (ApplicationRecord record : records) {
            applications.add(applicationFromRecord(record));
        }

        return applications;
    }

    public Application getApplication(String applicationId) {
        //potentially implement cache and cache check later with cache if found then cache logic...
        return applicationRepository.findById(applicationId)
                .map(this::applicationFromRecord)
                .orElse(null);
    }

    public void deleteApplication(String applicationId) {
        applicationRepository.deleteById(applicationId);
    }

    private Application applicationFromRecord(ApplicationRecord record) {
        try {
            return new Application(UUID.fromString(record.getUserId()),
                    UUID.fromString(record.getApplicationId()),
                    record.getTimestamp(),
                    mapper.readValue(record.getResumeAsJson(), Resume.class),
                    record.getWorkHistory(),
                    record.getReferences(),
                    mapper.readValue(record.getCriteriaAsJson(), Criteria.class));
        }
        catch (JsonProcessingException e) {
            //TODO maybe create unique runtime exception, so this can be more defined
            throw new RuntimeException("was unable to properly retrieve json values from database");
        }
    }

    private ApplicationRecord recordFromApplication(Application application) {
        try {
            ApplicationRecord record = new ApplicationRecord();

            record.setUserId(application.getUserId().toString());
            record.setApplicationId(application.getApplicationId().toString());
            record.setTimestamp(application.getTimestamp());
            record.setResumeAsJson(mapper.writeValueAsString(application.getResume()));
            record.setWorkHistory(application.getWorkHistory());
            record.setReferences(application.getReferences());
            record.setCriteriaAsJson(mapper.writeValueAsString(application.getJobCriteria()));

            return record;
        }
        catch (JsonProcessingException e) {
            //TODO maybe create unique runtime exception, so this can be more defined
            throw new RuntimeException("was unable to properly turn values into json");
        }
    }
}
