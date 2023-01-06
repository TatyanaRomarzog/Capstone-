package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.EmployerService;
import com.kenzie.appserver.service.exceptions.UsernameAlreadyTaken;
import com.kenzie.appserver.service.exceptions.UsernameOrPasswordIncorrect;
import com.kenzie.appserver.service.model.Employer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/employer")
public class EmployerController {

    private EmployerService employerService;

    EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @PostMapping
    public ResponseEntity<EmployerResponse> createEmployer(@RequestBody EmployerCreateRequest createRequest) {
        Employer employer = new Employer(createRequest.getUsername(),
                createRequest.getPassword(),
                createRequest.getFirstName(),
                createRequest.getMiddleName(),
                createRequest.getLastName(),
                createRequest.getCompanyName(),
                createRequest.getCompanyPhoneNumber(),
                createRequest.getCompanyEmail());

        try{
            employerService.createEmployerAccount(employer);

            return ResponseEntity.created(URI.create("/employer/" + employer.getUsername()))
                    .body(employerToResponse(employer));
        }
        catch (UsernameAlreadyTaken e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<EmployerResponse> updateEmployer(@PathVariable("username") String username,
                                                   @RequestBody EmployerUpdateRequest updateRequest) {
        Employer employer = new Employer(username,
                updateRequest.getNewPassword(),
                updateRequest.getFirstName(),
                updateRequest.getMiddleName(),
                updateRequest.getLastName(),
                updateRequest.getCompanyName(),
                updateRequest.getCompanyPhoneNumber(),
                updateRequest.getCompanyEmail());

        try{
            employerService.updateEmployerAccount(employer, updateRequest.getOldPassword());

            return ResponseEntity.ok(employerToResponse(employer));
        }
        catch (UsernameOrPasswordIncorrect e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<EmployerResponse> loginEmployer(@RequestBody EmployerLoginRequest loginRequest) {
        try {
            Employer employer = employerService.loginEmployer(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(employerToResponse(employer));
        }
        catch (UsernameOrPasswordIncorrect e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private EmployerResponse employerToResponse(Employer employer) {
        EmployerResponse response = new EmployerResponse();

        response.setUsername(employer.getUsername());
        response.setFirstName(employer.getFirstName());
        response.setMiddleName(employer.getMiddleName());
        response.setLastName(employer.getLastName());
        response.setCompanyName(employer.getCompanyName());
        response.setCompanyPhoneNumber(employer.getCompanyPhoneNumber());
        response.setCompanyEmail(employer.getCompanyEmail());

        return response;
    }
}
