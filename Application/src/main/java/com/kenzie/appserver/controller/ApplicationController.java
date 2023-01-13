package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.ApplicationService;
import com.kenzie.appserver.service.JobConnectionService;
import com.kenzie.appserver.service.model.Application;
import com.kenzie.appserver.service.model.Criteria;
import com.kenzie.appserver.service.model.Resume;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user/{username}/application")
public class ApplicationController {

    private ApplicationService applicationService;
    private JobConnectionService connectionService;

    ApplicationController(ApplicationService applicationService, JobConnectionService connectionService) {
        this.applicationService = applicationService;
        this.connectionService = connectionService;
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(@PathVariable("username") String username,
                                                                 @RequestBody ApplicationCreateRequest createRequest) {
        Application application = new Application(username,
                UUID.randomUUID(),
                LocalDateTime.now().toString(),
                new Resume(createRequest.getFirstName(),
                        createRequest.getLastName(),
                        createRequest.getHomeAddress(),
                        createRequest.getPhoneNumber(),
                        createRequest.getEmailAddress(),
                        createRequest.getObjective(),
                        createRequest.getEducation(),
                        createRequest.getExperience(),
                        createRequest.getSkills()),
                createRequest.getWorkHistory(),
                createRequest.getReferences(),
                new Criteria(createRequest.getPositionTitle(),
                        createRequest.getLocations(),
                        createRequest.getMinimumSalary(),
                        createRequest.getOpenJobsLimit())
        );

        applicationService.createApplication(application);

        ApplicationResponse response = applicationToResponse(application);

        return ResponseEntity.created(URI.create("/user/" + username + "/application/" +
                response.getApplicationId())).body(response);
    }

    @PutMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponse> updateApplication(@PathVariable("username") String username,
                                                                 @PathVariable("applicationId") String applicationId,
                                                                 @RequestBody ApplicationUpdateRequest updateRequest) {

        Application findApplication = applicationService.getApplication(applicationId);

        if(findApplication == null || !findApplication.getUsername().equals(username)) {
            return ResponseEntity.notFound().build();
        }

        Application applicationUdate = new Application(username,
                findApplication.getApplicationId(),
                LocalDateTime.now().toString(),
                new Resume(updateRequest.getFirstName(),
                        updateRequest.getLastName(),
                        updateRequest.getHomeAddress(),
                        updateRequest.getPhoneNumber(),
                        updateRequest.getEmailAddress(),
                        updateRequest.getObjective(),
                        updateRequest.getEducation(),
                        updateRequest.getExperience(),
                        updateRequest.getSkills()),
                updateRequest.getWorkHistory(),
                updateRequest.getReferences(),
                new Criteria(updateRequest.getPositionTitle(),
                        updateRequest.getLocations(),
                        updateRequest.getMinimumSalary(),
                        updateRequest.getOpenJobsLimit())
        );

        applicationService.updateApplication(applicationUdate);

        return ResponseEntity.ok(applicationToResponse(applicationUdate));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationResponse>> getAllApplications(@PathVariable("username") String username) {
        List<Application> allApplications = applicationService.getAllApplicationsForUser(username);
        if (allApplications == null || allApplications.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<ApplicationResponse> applicationResponses = new ArrayList<>();
        for (Application application : allApplications) {
            applicationResponses.add(applicationToResponse(application));
        }

        return ResponseEntity.ok(applicationResponses);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponse> getApplication(@PathVariable("username") String username,
                                                              @PathVariable("applicationId") String applicationId) {
        Application findApplication = applicationService.getApplication(applicationId);
        if (findApplication == null || !findApplication.getUsername().equals(username)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(applicationToResponse(findApplication));
    }

    @DeleteMapping("/{applicationId}")
    public ResponseEntity deleteApplication(@PathVariable("username") String username,
                                            @PathVariable("applicationId") String applicationId) {
        Application application = applicationService.getApplication(applicationId);

        if(application != null && application.getUsername().equals(username)) {
            applicationService.deleteApplication(applicationId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ApplicationResponse applicationToResponse(Application application) {
        Resume resume = application.getResume();
        ResumeResponse resumeResponse = new ResumeResponse();
        resumeResponse.setFirstName(resume.getFirstName());
        resumeResponse.setLastName(resume.getLastName());
        resumeResponse.setHomeAddress(resume.getHomeAddress());
        resumeResponse.setPhoneNumber(resume.getPhoneNumber());
        resumeResponse.setEmailAddress(resume.getEmailAddress());
        resumeResponse.setObjective(resume.getObjective());
        resumeResponse.setEducation(resume.getEducation());
        resumeResponse.setExperience(resume.getExperience());
        resumeResponse.setSkills(resume.getSkills());

        Criteria criteria = application.getJobCriteria();
        CriteriaResponse criteriaResponse = new CriteriaResponse();
        criteriaResponse.setPositionTitle(criteria.getPositionTitle());
        criteriaResponse.setLocations(criteria.getLocations());
        criteriaResponse.setMinimumSalary(criteria.getMinimumSalary());
        criteriaResponse.setOpenJobsLimit(criteria.getOpenJobsLimit());

        ApplicationResponse applicationResponse = new ApplicationResponse();
        applicationResponse.setApplicationId(application.getApplicationId().toString());
        applicationResponse.setTimestamp(application.getTimestamp());
        applicationResponse.setResume(resumeResponse);
        applicationResponse.setWorkHistory(application.getWorkHistory());
        applicationResponse.setReferences(application.getReferences());
        applicationResponse.setJobCriteria(criteriaResponse);

        return applicationResponse;
    }
}