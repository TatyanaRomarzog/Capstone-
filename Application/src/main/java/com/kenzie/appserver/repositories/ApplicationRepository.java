package com.kenzie.appserver.repositories;

import com.kenzie.appserver.repositories.model.ApplicationRecord;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<ApplicationRecord, String> {
}
