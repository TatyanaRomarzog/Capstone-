package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.JobPostRecord;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface JobPostRepository extends CrudRepository<JobPostRecord, String> {

    List<JobPostRecord> findByEmployerUsername(String employerUsername);

    List<JobPostRecord> findByPositionTitle(String positionTitle);
}
