package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.*;
import com.kenzie.appserver.service.JobConnectionService;
import com.kenzie.appserver.service.JobPostService;
import com.kenzie.appserver.service.model.JobPost;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employer/{employerUsername}/jobpost")
public class JobPostController {

    private JobPostService jobPostService;
    private JobConnectionService connectionService;

    JobPostController(JobPostService jobPostService, JobConnectionService connectionService) {
        this.jobPostService = jobPostService;
        this.connectionService = connectionService;
    }

    @PostMapping
    public ResponseEntity<JobPostResponse> createJobPost(@PathVariable("employerUsername") String employerUsername,
                                                                 @RequestBody JobPostCreateRequest createRequest) {
        JobPost jobPost = new JobPost(employerUsername,
                UUID.randomUUID(),
                LocalDateTime.now().toString(),
                createRequest.getPositionTitle(),
                createRequest.getCompanyName(),
                createRequest.getLocation(),
                createRequest.getProposedSalary(),
                createRequest.getDescription(),
                createRequest.getFullTime());

        jobPostService.createJobPost(jobPost);

        JobPostResponse response = jobPostToResponse(jobPost);

        return ResponseEntity.created(URI.create("/employer/" + employerUsername + "/jobpost/" +
                response.getJobPostId())).body(response);
    }

    @PutMapping("/{jobPostId}")
    public ResponseEntity<JobPostResponse> updateJobPost(@PathVariable("employerUsername") String employerUsername,
                                                                 @PathVariable("jobPostId") String jobPostId,
                                                                 @RequestBody JobPostUpdateRequest updateRequest) {
        JobPost findJobPost = jobPostService.getJobPost(jobPostId);

        if (findJobPost == null || !findJobPost.getEmployerUsername().equals(employerUsername)) {
            return ResponseEntity.notFound().build();
        }

        JobPost jobPostUpdate = new JobPost(employerUsername,
                findJobPost.getJobPostId(),
                LocalDateTime.now().toString(),
                updateRequest.getPositionTitle(),
                updateRequest.getCompanyName(),
                updateRequest.getLocation(),
                updateRequest.getProposedSalary(),
                updateRequest.getDescription(),
                updateRequest.getFullTime());

        jobPostService.updateJobPost(jobPostUpdate);

        return ResponseEntity.ok(jobPostToResponse(jobPostUpdate));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobPostResponse>> getAllJobPosts(@PathVariable("employerUsername") String employerUsername) {
        List<JobPost> allJobPosts = jobPostService.getAllJobPostsForEmployer(employerUsername);

        if (allJobPosts == null || allJobPosts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<JobPostResponse> jobPostResponses = new ArrayList<>();
        for (JobPost jobPost : allJobPosts) {
            jobPostResponses.add(jobPostToResponse(jobPost));
        }

        return ResponseEntity.ok(jobPostResponses);
    }

    @GetMapping("/{jobPostId}")
    public ResponseEntity<JobPostResponse> getJobPost(@PathVariable("employerUsername") String employerUsername,
                                                              @PathVariable("jobPostId") String jobPostId) {
        JobPost findJobPost = jobPostService.getJobPost(jobPostId);

        if (findJobPost == null || !findJobPost.getEmployerUsername().equals(employerUsername)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(jobPostToResponse(findJobPost));
    }

    @DeleteMapping("/{jobPostId}")
    public ResponseEntity deleteJobPost(@PathVariable("employerUsername") String employerUsername,
                                            @PathVariable("jobPostId") String jobPostId) {
        JobPost jobPost = jobPostService.getJobPost(jobPostId);

        if (jobPost != null && jobPost.getEmployerUsername().equals(employerUsername)) {
            jobPostService.deleteJobPost(jobPostId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private JobPostResponse jobPostToResponse(JobPost jobPost) {
        JobPostResponse jobPostResponse = new JobPostResponse();

        jobPostResponse.setJobPostId(jobPost.getJobPostId().toString());
        jobPostResponse.setTimestamp(jobPost.getTimestamp());
        jobPostResponse.setPositionTitle(jobPost.getPositionTitle());
        jobPostResponse.setCompanyName(jobPost.getCompanyName());
        jobPostResponse.setLocation(jobPost.getLocation());
        jobPostResponse.setProposedSalary(jobPost.getProposedSalary());
        jobPostResponse.setDescription(jobPost.getDescription());
        jobPostResponse.setFullTime(jobPost.getFullTime());

        return jobPostResponse;
    }
}
