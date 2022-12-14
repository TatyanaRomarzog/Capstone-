package com.kenzie.appserver.controller;

import com.kenzie.appserver.Application;
import com.kenzie.appserver.controller.model.ApplicationResponse;
import com.kenzie.appserver.service.ApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
@RequestMapping("/cases/{caseId}/evidence")
public class ApplicationController {

    private ApplicationService applicationService;

    ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ApplicationResponse>> getAllEvidenceForCase(@PathVariable("caseId") String caseId) {
        List<Application> allEvidence = ApplicationService.findAllEvidenceForCase(caseId);
        if (allEvidence == null || allEvidence.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<EvidenceResponse> evidenceResponses = new ArrayList<>();
        for (Evidence evidence : allEvidence) {
            evidenceResponses.add(evidenceResponse(evidence));
        }

        return ResponseEntity.ok(evidenceResponses);
    }

    @GetMapping("/{evidenceId}")
    public ResponseEntity<EvidenceResponse> getEvidenceById(@PathVariable("caseId") String caseId,
                                                            @PathVariable("evidenceId") String evidenceId) {
        Evidence evidence = evidenceService.findEvidenceByEvidenceId(evidenceId);
        if (evidence == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(evidenceResponse(evidence));
    }

    @PostMapping
    public ResponseEntity<EvidenceResponse> createEvidence(@PathVariable("caseId") String caseId,
                                                           @RequestBody EvidenceCreateRequest createRequest) {
        Evidence evidence = new Evidence(UUID.fromString(caseId),
                randomUUID(),
                LocalDateTime.now().toString(),
                createRequest.getLocation(),
                createRequest.getTimeDate(),
                createRequest.getAuthor(),
                createRequest.getDescription());
        evidenceService.addNewEvidence(evidence);

        EvidenceResponse evidenceResponse = evidenceResponse(evidence);

        return ResponseEntity.created(URI.create("/cases/" + caseId + "/evidence/" +
                evidenceResponse.getEvidenceId())).body(evidenceResponse);
    }

    private EvidenceResponse evidenceResponse(Evidence evidence) {
        EvidenceResponse response = new EvidenceResponse();
        response.setCaseId(evidence.getCaseId().toString());
        response.setEvidenceId(evidence.getEvidenceId().toString());
        response.setTimeStamp(evidence.getTimeStamp());
        response.setAuthor(evidence.getAuthor());
        response.setDescription(evidence.getDescription());
        response.setLocation(evidence.getLocation());
        response.setTimeDate(evidence.getTimeDate());

        return response;
    }
}
