package com.kenzie.capstone.service;

import com.kenzie.capstone.service.dao.JobConnectionDao;
import com.kenzie.capstone.service.model.ExampleData;
import com.kenzie.capstone.service.dao.ExampleDao;
import com.kenzie.capstone.service.model.ExampleRecord;
import com.kenzie.capstone.service.model.JobConnectionData;
import com.kenzie.capstone.service.model.JobConnectionRecord;

import javax.inject.Inject;

import java.util.List;
import java.util.UUID;

public class LambdaService {

    private ExampleDao exampleDao;
    private JobConnectionDao connectionDao;

    @Inject
    public LambdaService(ExampleDao exampleDao, JobConnectionDao connectionDao) {
        this.exampleDao = exampleDao;
        this.connectionDao = connectionDao;
    }

    public ExampleData getExampleData(String id) {
        List<ExampleRecord> records = exampleDao.getExampleData(id);
        if (records.size() > 0) {
            return new ExampleData(records.get(0).getId(), records.get(0).getData());
        }
        return null;
    }

    public JobConnectionData getJobConnectionData(String connectionId) {
        List<JobConnectionRecord> records = connectionDao.getJobConnectionData(connectionId);
        if (records.size() > 0) {
            JobConnectionRecord record = records.get(0);
            return new JobConnectionData(record.getConnectionId(),
                    record.getApplicationId(),
                    record.getJobPostId(),
                    record.getEmployerUsername(),
                    record.getApplicantUsername(),
                    record.getStatus());
        }
        return null;
    }

    public ExampleData setExampleData(String data) {
        String id = UUID.randomUUID().toString();
        ExampleRecord record = exampleDao.setExampleData(id, data);
        return new ExampleData(id, data);
    }
}
