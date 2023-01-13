package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.exceptions.UsernameAlreadyTaken;
import com.kenzie.appserver.service.exceptions.UsernameOrPasswordIncorrect;
import com.kenzie.appserver.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createAccount(User newUser) {
        if(userRepository.existsById(newUser.getUsername())) {
            throw new UsernameAlreadyTaken("Username is already taken");
        }

        userRepository.save(recordFromUser(newUser));

        return newUser;
    }

    public void updateAccount(User updatedUser, String oldPassword) {
        UserRecord oldRecord = userRepository.findById(updatedUser.getUsername())
                .orElseThrow(() -> new UsernameOrPasswordIncorrect("username does not match any user"));

        if(!oldRecord.getPassword().equals(oldPassword)) {
            throw new UsernameOrPasswordIncorrect("wrong password, changes won't update");
        }

        userRepository.save(recordFromUser(updatedUser));
    }

    public User loginUser(String username, String password) {
        UserRecord record = userRepository.findById(username).orElse(null);

        if(record == null || !record.getPassword().equals(password)) {
            throw new UsernameOrPasswordIncorrect("username or password is incorrect");
        }

        return userFromRecord(record);
    }

    private UserRecord recordFromUser(User user) {
        UserRecord record = new UserRecord();

        record.setUsername(user.getUsername());
        record.setPassword(user.getPassword());
        record.setFirstName(user.getFirstName());
        record.setMiddleName(user.getMiddleName());
        record.setLastName(user.getLastName());
        record.setPhoneNumber(user.getPhoneNumber());
        record.setPrimaryEmail(user.getPrimaryEmail());

        return record;
    }

    private User userFromRecord(UserRecord record) {
        return new User(record.getUsername(),
                record.getPassword(),
                record.getFirstName(),
                record.getMiddleName(),
                record.getLastName(),
                record.getPhoneNumber(),
                record.getPrimaryEmail());
    }
}
