package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.JobConnectionData;
import com.kenzie.capstone.service.model.JobConnectionDataRequest;
import com.kenzie.capstone.service.model.JobConnectionDataUpdate;
import com.kenzie.capstone.service.model.JobConnectionResponse;

import java.util.List;

public class JobConnectionServiceClient {

    //private static final String GET_JOBCONNECTION_ENDPOINT = "jobconnection/{connectionId}";
    //private static final String SET_JOBCONNECTION_ENDPOINT = "jobconnection";

    private static final String GET_CONNECTION_ENDPOINT = "jobconnection/{connectionId}";
    private static final String GET_ALL_USER_CONNECTIONS = "jobconnection/user/{username}";
    private static final String GET_ALL_EMPLOYER_CONNECTIONS = "jobconnection/employer/{employerUsername}";
    //need to have endpoint that deletes based on applicationId and one that deletes based on job post id
    private static final String DELETE_CONNECTION_ENDPOINT = "jobconnection/{connectionId}";
    private static final String DELETE_CONNECTIONS_FOR_APPLICATION = "jobconnection/application/{ApplicationId}";
    private static final String DELETE_CONNECTIONS_FOR_JOBPOST = "jobconnection/jobpost/{jobPostId}";
    private static final String POST_CONNECTIONS_ENDPOINT = "jobconnection";

    private static final String UPDATE_CONNECTION_ENDPOINT = "jobconnection/{connectionId}";


    private ObjectMapper mapper;

    public JobConnectionServiceClient() {
        this.mapper = new ObjectMapper();
    }

    public JobConnectionData getJobConnectionData(String connectionId) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_CONNECTION_ENDPOINT)
                .replace("{connectionId}", connectionId);
        JobConnectionData connectionData;
        try {
            connectionData = mapper.readValue(response, JobConnectionData.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize Json: " + e);
        }
        return connectionData;
    }

    public List<JobConnectionData> getAllJobConnectionDataForUser(String username) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_ALL_USER_CONNECTIONS.replace("{username}", username));
        List<JobConnectionData> dataForUser;
        try {
            dataForUser = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return dataForUser;
    }

    public List<JobConnectionData> getAllJobConnectionDataForEmployer(String employerUsername) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String response = endpointUtility.getEndpoint(GET_ALL_EMPLOYER_CONNECTIONS
                .replace("{employerUsername}", employerUsername));
        List<JobConnectionData> dataForUser;
        try {
            dataForUser = mapper.readValue(response, new TypeReference<>(){});
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return dataForUser;
    }

    public void deleteJobConnectionData(String connectionId) {

    }

    public void deleteJobConnectionDataForApplication(String applicationId) {

    }

    public void deleteJobConnectionDataForJobPost(String jobPostId) {

    }

    public JobConnectionResponse createJobConnection(JobConnectionDataRequest connectionRequest) {
        EndpointUtility endpointUtility = new EndpointUtility();
        String request;
        try {
            request = mapper.writeValueAsString(connectionRequest);
        } catch(JsonProcessingException e) {
            throw new ApiGatewayException("Unable to serialize request: " + e);
        }
        String response = endpointUtility.postEndpoint(POST_CONNECTIONS_ENDPOINT, request);
        JobConnectionResponse connectionResponse;
        try {
            connectionResponse = mapper.readValue(response, JobConnectionResponse.class);
        } catch (Exception e) {
            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
        }
        return connectionResponse;
    }

    public JobConnectionResponse updateJobConnection(JobConnectionDataUpdate updateRequest) {
        return new JobConnectionResponse();
    }



    //todo check what is going on after this comment??


//
//    public JobConnectionData setJobConnectionData(String setData) {
//        EndpointUtility endpointUtility = new EndpointUtility();
//        String response = endpointUtility.postEndpoint(SET_JOBCONNECTION_ENDPOINT, setData);
//        JobConnectionData connectionData;
//        try {
//            connectionData = mapper.readValue(response, JobConnectionData.class);
//        }
//        catch (Exception e) {
//            throw new ApiGatewayException("Unable to map deserialize JSON: " + e);
//        }
//        return connectionData;
//    }
}