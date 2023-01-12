package com.kenzie.capstone.service.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.google.common.collect.ImmutableMap;
import com.kenzie.capstone.service.model.ExampleRecord;
import com.kenzie.capstone.service.model.JobConnectionData;
import com.kenzie.capstone.service.model.JobConnectionRecord;

import java.util.List;

public class JobConnectionDao {
    private DynamoDBMapper mapper;

    public JobConnectionDao(DynamoDBMapper mapper) {
        this.mapper = mapper;
    }

    public JobConnectionData storeConnectionData(JobConnectionData connectionData) {
        try {
            mapper.save(connectionData, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "connectionId",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        }
        catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("the job connection id has already been used");
        }

        return connectionData;
    }

    public List<JobConnectionRecord> getJobConnectionData(String connectionId) {
        JobConnectionRecord connectionRecord = new JobConnectionRecord();
        connectionRecord.setConnectionId(connectionId);

        DynamoDBQueryExpression<JobConnectionRecord> queryExpression =
                new DynamoDBQueryExpression<JobConnectionRecord>()
                .withHashKeyValues(connectionRecord)
                .withConsistentRead(false);

        return mapper.query(JobConnectionRecord.class, queryExpression);
    }

    //public List<>

    public ExampleRecord setExampleData(String id, String data) {
        ExampleRecord exampleRecord = new ExampleRecord();
        exampleRecord.setId(id);
        exampleRecord.setData(data);

        try {
            mapper.save(exampleRecord, new DynamoDBSaveExpression()
                    .withExpected(ImmutableMap.of(
                            "id",
                            new ExpectedAttributeValue().withExists(false)
                    )));
        } catch (ConditionalCheckFailedException e) {
            throw new IllegalArgumentException("id already exists");
        }

        return exampleRecord;
    }

}
