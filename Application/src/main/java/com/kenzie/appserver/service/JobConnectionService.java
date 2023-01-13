package com.kenzie.appserver.service;

import com.kenzie.appserver.service.model.ConnectionForUser;
import com.kenzie.capstone.service.client.LambdaServiceClient;
import com.kenzie.capstone.service.model.JobConnectionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobConnectionService {
    ApplicationService applicationService;
    JobPostService jobPostService;
    LambdaServiceClient serviceClient;

    @Autowired
    public JobConnectionService(ApplicationService applicationService,
                                JobPostService jobPostService,
                                LambdaServiceClient serviceClient) {
        this.applicationService = applicationService;
        this.jobPostService = jobPostService;
        this.serviceClient = serviceClient;
    }

    public List<ConnectionForUser> getAllJobsForUser(String username) {
        List<ConnectionForUser> jobsForUser = new ArrayList<>();
        List<JobConnectionData> dataFromLambda = serviceClient.getAllJobConnectionDataForUser(username);

        for(JobConnectionData data : dataFromLambda) {
            jobsForUser.add(new ConnectionForUser(data.getConnectionId(),
                    data.getApplicationId(),
                    data.getStatus(),
                    jobPostService.getJobPost(data.getJobPostId())));
        }

        return jobsForUser;
    }

    public ConnectionForUser getJobForUser(String connectionId) {
        JobConnectionData data = serviceClient.getJobConnectionData(connectionId);

        ConnectionForUser jobForUser = new ConnectionForUser(data.getConnectionId(),
                data.getApplicationId(),
                data.getStatus(),
                jobPostService.getJobPost(data.getJobPostId()));

        return jobForUser;
    }
}
