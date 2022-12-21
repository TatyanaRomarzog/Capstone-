package com.kenzie.appserver.controller;

import com.kenzie.appserver.Application;
import com.kenzie.appserver.controller.model.ApplicationCreateRequest;
import com.kenzie.appserver.controller.model.ApplicationResponse;
import com.kenzie.appserver.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
//TODO: fix the endpoints

@RestController
@RequestMapping("/cases/{caseId}/evidence")
public class ApplicationController {

    private ApplicationService applicationService;

    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationResponse>> getAllApplilcations(@PathVariable("applicationId") String applicationId) {
        List<Application> allApplications = Collections.singletonList(ApplicationService.getAllApplications(applicationId));
        if (allApplications == null || allApplications.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<Application> applicationResponses = new ArrayList<>();
        for (Application applications : allApplications) {
            applicationResponses.add(applicationResponses(applicationId, applications));
        }

        return ResponseEntity.ok(applicationResponses);
    }

    @GetMapping("/{evidenceId}")
    public ResponseEntity<ApplicationResponse> getApplication(@PathVariable("applicationId") String applicationId,
                                                            @PathVariable("userId") String userId) {
//        TODO: need to create a proper getApplication method
        Application application = ApplicationService.getAllApplications(applicationId);
        if (evidence == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evidenceResponse(applicationId));
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> createApplication(@PathVariable("applicationId") String applicationId,
                                                           @RequestBody ApplicationCreateRequest createRequest) {
        Application application = new Application(UUID.fromString(applicationId),
                randomUUID(),
                LocalDateTime.now().toString(),
                createRequest.getApplicationId(),
                createRequest.getTimeStamp(),
                createRequest.getResume(),
                createRequest.getReference(),
                createRequest.getWorkHistory(),
                createRequest.getJobCriteria(),
                createRequest.getCriteria(),

        applicationService.addNewApplication(applicationId, createRequest));

        ApplicationResponse applicationResponse = applicationResponse(applicationId);

        return ResponseEntity.created(URI.create("/cases/" + caseId + "/evidence/" +
                evidenceResponse.getEvidenceId())).body(evidenceResponse);
    }
//        TODO: Have Elise look at this and see if it needs to be a record or a response and if we need to create setters in Application
    private ApplicationRecord applicationResponse(Application applicationId) {
        ApplicationRecord response = new ApplicationRecord();
        response.setApplicationId(applicationId.getApplicationId().toString());
        response.setTimeStamp(applicationId.getTimeStamp());
        response.setResume(applicationId.getResume());
        response.setReferences(applicationId.getReferences());
        response.setWorkHistory(applicationId.getWorkHistory());
        response.setJobCriteria(applicationId.getJobCriteria());
        response.setCriteria(applicationId.getCriteria());


        return response;
    }
}
