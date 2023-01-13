package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.JobPostRepository;
import com.kenzie.appserver.repositories.model.JobPostRecord;
import com.kenzie.appserver.service.model.JobPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class JobPostService {
    private JobPostRepository jobPostRepository;

    @Autowired
    public JobPostService(JobPostRepository jobPostRepository) {
        this.jobPostRepository = jobPostRepository;
    }

    public JobPost createJobPost(JobPost jobPost) {
        jobPostRepository.save(recordFromJobPost(jobPost));
        return jobPost;
    }

    public void updateJobPost(JobPost jobPostToUpdate) {
        if (jobPostRepository.existsById(jobPostToUpdate.getJobPostId().toString())) {
            jobPostRepository.save(recordFromJobPost(jobPostToUpdate));
        }
    }

    public List<JobPost> getAllJobPostsForEmployer(String employerUsername) {
        List<JobPost> jobPosts = new ArrayList<>();

        List<JobPostRecord> records = jobPostRepository.findByEmployerUsername(employerUsername);
        for (JobPostRecord record : records) {
            jobPosts.add(jobPostFromRecord(record));
        }

        return jobPosts;
    }

    public List<JobPost> getAllJobPostsByPositionTitle(String positionTitle) {
        List<JobPost> jobPosts = new ArrayList<>();

        List<JobPostRecord> records = jobPostRepository.findByPositionTitle(positionTitle);
        for (JobPostRecord record : records) {
            jobPosts.add(jobPostFromRecord(record));
        }

        return jobPosts;
    }

    public JobPost getJobPost(String jobPostId) {
        //potentially implement cache and cache check later with cache if found then cache logic...
        return jobPostRepository.findById(jobPostId)
                .map(this::jobPostFromRecord)
                .orElse(null);
    }

    public void deleteJobPost(String jobPostId) {
        jobPostRepository.deleteById(jobPostId);
        // TODO when someone deletes a job post, we need to delete all job responses for that application
        //  so they don't clutter users boxes
    }

    private JobPost jobPostFromRecord(JobPostRecord record) {
        return new JobPost(record.getEmployerUsername(),
                UUID.fromString(record.getJobPostId()),
                record.getTimestamp(),
                record.getPositionTitle(),
                record.getCompanyName(),
                record.getLocation(),
                record.getProposedSalary(),
                record.getDescription(),
                record.getFullTime());
    }

    private JobPostRecord recordFromJobPost(JobPost jobPost) {
        JobPostRecord record = new JobPostRecord();

        record.setEmployerUsername(jobPost.getEmployerUsername());
        record.setJobPostId(jobPost.getJobPostId().toString());
        record.setTimestamp(jobPost.getTimestamp());
        record.setPositionTitle(jobPost.getPositionTitle());
        record.setCompanyName(jobPost.getCompanyName());
        record.setLocation(jobPost.getLocation());
        record.setProposedSalary(jobPost.getProposedSalary());
        record.setDescription(jobPost.getDescription());
        record.setFullTime(jobPost.getFullTime());

        return record;
    }
}
