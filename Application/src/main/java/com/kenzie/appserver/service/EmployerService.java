package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.EmployerRepository;
import com.kenzie.appserver.repositories.model.EmployerRecord;
import com.kenzie.appserver.service.exceptions.UsernameAlreadyTaken;
import com.kenzie.appserver.service.exceptions.UsernameOrPasswordIncorrect;
import com.kenzie.appserver.service.model.Employer;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployerService {
    private EmployerRepository employerRepository;

    @Autowired
    public EmployerService(EmployerRepository employerRepository) {
        this.employerRepository = employerRepository;
    }

    public Employer createEmployerAccount(Employer newEmployer) {
        if(employerRepository.existsById(newEmployer.getUsername())) {
            throw new UsernameAlreadyTaken("Username is already taken");
        }

        employerRepository.save(recordFromEmployer(newEmployer));

        return newEmployer;
    }

    public void updateEmployerAccount(Employer updatedEmployer, String oldPassword) {
        EmployerRecord oldRecord = employerRepository.findById(updatedEmployer.getUsername())
                .orElseThrow(() -> new UsernameOrPasswordIncorrect("username does not match any employer"));

        if(!oldRecord.getPassword().equals(oldPassword)) {
            throw new UsernameOrPasswordIncorrect("wrong password, changes won't update");
        }

        employerRepository.save(recordFromEmployer(updatedEmployer));
    }

    public Employer loginEmployer(String username, String password) {
        EmployerRecord record = employerRepository.findById(username).orElse(null);

        if(record == null || !record.getPassword().equals(password)) {
            throw new UsernameOrPasswordIncorrect("username or password is incorrect");
        }

        return employerFromRecord(record);
    }

    private EmployerRecord recordFromEmployer(Employer employer) {
        EmployerRecord record = new EmployerRecord();

        record.setUsername(employer.getUsername());
        record.setPassword(employer.getPassword());
        record.setFirstName(employer.getFirstName());
        record.setMiddleName(employer.getMiddleName());
        record.setLastName(employer.getLastName());
        record.setCompanyName(employer.getCompanyName());
        record.setCompanyPhoneNumber(employer.getCompanyPhoneNumber());
        record.setCompanyEmail(employer.getCompanyEmail());

        return record;
    }

    private Employer employerFromRecord(EmployerRecord record) {
        return new Employer(record.getUsername(),
                record.getPassword(),
                record.getFirstName(),
                record.getMiddleName(),
                record.getLastName(),
                record.getCompanyName(),
                record.getCompanyPhoneNumber(),
                record.getCompanyEmail());
    }
}
