package com.kenzie.appserver.service.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.File;
import java.security.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;



    //Application Class:
    //UUID applicationId *Partition key*
    //Timestamp timeStamp (time that application was most recently edited)
    //File Resume
    //List<String> workHistory
    //List<String> references
    //Criteria jobCriteria

    //constructor for all arguments
    //getters, no setters
    public class Application {
}
