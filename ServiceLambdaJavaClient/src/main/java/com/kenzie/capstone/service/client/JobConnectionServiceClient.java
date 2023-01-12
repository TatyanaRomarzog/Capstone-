package com.kenzie.capstone.service.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.capstone.service.model.JobConnectionData;

import java.util.List;

public class JobConnectionServiceClient {

    //private static final String GET_JOBCONNECTION_ENDPOINT = "jobconnection/{connectionId}";
    //private static final String SET_JOBCONNECTION_ENDPOINT = "jobconnection";

    private static final String GET_CONNECTION_ENDPOINT = "jobconnection/{connectionId}";
    private static final String GET_ALL_USER_CONNECTIONS = "jobconnection/user/{username}";
            //"user/{username}/jobconnection/all";

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
