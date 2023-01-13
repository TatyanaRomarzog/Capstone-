package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ApplicationRecord;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ApplicationRepository extends CrudRepository<ApplicationRecord, String> {
<<<<<<< HEAD
=======

>>>>>>> main
    List<ApplicationRecord> findByUsername(String username);
}
