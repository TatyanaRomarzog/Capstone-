package com.kenzie.appserver.service;

import com.kenzie.appserver.repositories.UserRepository;
import com.kenzie.appserver.repositories.model.UserRecord;
import com.kenzie.appserver.service.exceptions.UsernameAlreadyTaken;
import com.kenzie.appserver.service.exceptions.UsernameOrPasswordIncorrect;
import com.kenzie.appserver.service.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    /** ------------------------------------------------------------------------
     *  userService.createAccount
     *  ------------------------------------------------------------------------ **/

    @Test
    void createAccount_newUser_savedAndReturned() {
        //GIVEN
        String username = "callMe";
        User newUser = new User(username, "password", "jenny", "",
                "tutone", "867-5309", "youveGotMyEmail@gmail.com");

        when(userRepository.existsById(newUser.getUsername())).thenReturn(false);
        ArgumentCaptor<UserRecord> recordCaptor = ArgumentCaptor.forClass(UserRecord.class);

        //WHEN
        userService.createAccount(newUser);

        //THEN
        verify(userRepository, times(1)).existsById(username);
        verify(userRepository).save(recordCaptor.capture());
        UserRecord record = recordCaptor.getValue();

        Assertions.assertEquals(newUser.getUsername(), record.getUsername(),
                "username should match one saved");
        Assertions.assertEquals(newUser.getPassword(), record.getPassword(),
                "password should match one saved");
        Assertions.assertEquals(newUser.getFirstName(), record.getFirstName(),
                "firstname should match one saved");
        Assertions.assertEquals(newUser.getMiddleName(), record.getMiddleName(),
                "middlename should match one saved");
        Assertions.assertEquals(newUser.getLastName(), record.getLastName(),
                "lastname should match one saved");
        Assertions.assertEquals(newUser.getPhoneNumber(), record.getPhoneNumber(),
                "phone number should match one saved");
        Assertions.assertEquals(newUser.getPrimaryEmail(), record.getPrimaryEmail(),
                "email should match one saved");
    }

    @Test
    void createAccount_usernameAlreadyTaken_throwsError() {
        //GIVEN
        String usernameAlreadyTaken = "callMe";
        User takenUser = new User(usernameAlreadyTaken, "password", "jenny", "",
                "tutone", "867-5309", "youveGotMyEmail@gmail.com");

        when(userRepository.existsById(takenUser.getUsername())).thenReturn(true);

        //WHEN THEN
        Assertions.assertThrows(UsernameAlreadyTaken.class, () -> userService.createAccount(takenUser),
                "user name should have been taken, throwing error");
        verify(userRepository, times(1)).existsById(usernameAlreadyTaken);
        verify(userRepository, times(0)).save(any());
    }

    /** ------------------------------------------------------------------------
     *  userService.updateAccount
     *  ------------------------------------------------------------------------ **/

    @Test
    void updateAccount_existingUser_savesUpdates() {
        //GIVEN
        String username = "callMe";
        String oldPassword = "password";

        UserRecord oldRecord = new UserRecord();
        oldRecord.setUsername(username);
        oldRecord.setPassword(oldPassword);
        oldRecord.setFirstName("jenny");
        oldRecord.setMiddleName("");
        oldRecord.setLastName("tutone");
        oldRecord.setPhoneNumber("867-5309");
        oldRecord.setPrimaryEmail("youveGotMyEmail@gmail.com");

        User userUpdates = new User(username, "changed my number", "jenny", "",
                "tutone", "867-1309", "youDontGotMyEmail@gmail.com");

        when(userRepository.findById(username)).thenReturn(Optional.of(oldRecord));
        ArgumentCaptor<UserRecord> recordCaptor = ArgumentCaptor.forClass(UserRecord.class);

        //WHEN
        userService.updateAccount(userUpdates, oldPassword);

        //THEN
        verify(userRepository, times(1)).findById(username);
        verify(userRepository).save(recordCaptor.capture());
        UserRecord newRecord = recordCaptor.getValue();

        Assertions.assertNotEquals(oldRecord, newRecord, "the updates should not match old record");
        Assertions.assertEquals(userUpdates.getUsername(), newRecord.getUsername(),
                "username should match one saved");
        Assertions.assertEquals(userUpdates.getPassword(), newRecord.getPassword(),
                "password should match one saved");
        Assertions.assertEquals(userUpdates.getFirstName(), newRecord.getFirstName(),
                "firstname should match one saved");
        Assertions.assertEquals(userUpdates.getMiddleName(), newRecord.getMiddleName(),
                "middlename should match one saved");
        Assertions.assertEquals(userUpdates.getLastName(), newRecord.getLastName(),
                "lastname should match one saved");
        Assertions.assertEquals(userUpdates.getPhoneNumber(), newRecord.getPhoneNumber(),
                "phone number should match one saved");
        Assertions.assertEquals(userUpdates.getPrimaryEmail(), newRecord.getPrimaryEmail(),
                "email should match one saved");
    }

    @Test
    void updateAccount_accountNotFound_throwsError() {
        //GIVEN
        String usernameNotFound = "whos on first";
        String oldPassword = "password";

        User userUpdates = new User(usernameNotFound, "password", "nobody", "",
                "not found", "404", "youDontGotMyEmail@gmail.com");

        when(userRepository.findById(usernameNotFound)).thenReturn(Optional.empty());

        //WHEN THEN
        Assertions.assertThrows(UsernameOrPasswordIncorrect.class,
                () -> userService.updateAccount(userUpdates, oldPassword),
                "account not found, should have thrown error");
        verify(userRepository, times(1)).findById(usernameNotFound);
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void updateAccount_passwordDoesNotMatchAccount_throwsError() {
        //GIVEN
        String username = "callMe";
        String oldPassword = "password";
        String wrongPassword = "notPassword";

        UserRecord oldRecord = new UserRecord();
        oldRecord.setUsername(username);
        oldRecord.setPassword(oldPassword);
        oldRecord.setFirstName("jenny");
        oldRecord.setMiddleName("");
        oldRecord.setLastName("tutone");
        oldRecord.setPhoneNumber("867-5309");
        oldRecord.setPrimaryEmail("youveGotMyEmail@gmail.com");

        User userUpdates = new User(username, "changed my number", "jenny", "",
                "tutone", "867-1309", "youDontGotMyEmail@gmail.com");

        when(userRepository.findById(username)).thenReturn(Optional.of(oldRecord));

        //WHEN THEN
        Assertions.assertThrows(UsernameOrPasswordIncorrect.class,
                () -> userService.updateAccount(userUpdates, wrongPassword),
                "passwords don't match, should have thrown error");
        verify(userRepository, times(1)).findById(username);
        verify(userRepository, times(0)).save(any());
    }

    /** ------------------------------------------------------------------------
     *  userService.loginUser
     *  ------------------------------------------------------------------------ **/

    @Test
    void loginUser_usernameAndPasswordMatch_returnsUser() {
        //GIVEN
        String username = "callMe";
        String password = "password";

        User existingUser = new User(username, password, "jenny", "",
                "tutone", "867-5309", "youveGotMyEmail@gmail.com");

        UserRecord existingUserRecord = new UserRecord();
        existingUserRecord.setUsername(username);
        existingUserRecord.setPassword(password);
        existingUserRecord.setFirstName("jenny");
        existingUserRecord.setMiddleName("");
        existingUserRecord.setLastName("tutone");
        existingUserRecord.setPhoneNumber("867-5309");
        existingUserRecord.setPrimaryEmail("youveGotMyEmail@gmail.com");


        when(userRepository.findById(username)).thenReturn(Optional.of(existingUserRecord));

        //WHEN
        User returnedUser = userService.loginUser(username, password);

        //THEN
        verify(userRepository, times(1)).findById(username);


        Assertions.assertEquals(existingUser.getUsername(), returnedUser.getUsername(),
                "username should match one returned");
        Assertions.assertEquals(existingUser.getPassword(), returnedUser.getPassword(),
                "password should match one returned");
        Assertions.assertEquals(existingUser.getFirstName(), returnedUser.getFirstName(),
                "firstname should match one returned");
        Assertions.assertEquals(existingUser.getMiddleName(), returnedUser.getMiddleName(),
                "middlename should match one returned");
        Assertions.assertEquals(existingUser.getLastName(), returnedUser.getLastName(),
                "lastname should match one returned");
        Assertions.assertEquals(existingUser.getPhoneNumber(), returnedUser.getPhoneNumber(),
                "phone number should match one returned");
        Assertions.assertEquals(existingUser.getPrimaryEmail(), returnedUser.getPrimaryEmail(),
                "email should match one returned");
    }

    @Test
    void loginUser_accountNotFound_throwsError() {
        //GIVEN
        String usernameNotFound = "not found";
        String password = "404";

        when(userRepository.findById(usernameNotFound)).thenReturn(Optional.empty());

        //WHEN THEN
        Assertions.assertThrows(UsernameOrPasswordIncorrect.class,
                () -> userService.loginUser(usernameNotFound, password),
                "account not found, should have throw error");
        verify(userRepository, times(1)).findById(usernameNotFound);
    }

    @Test
    void loginUser_passwordsDontMatch_throwsError() {
        //GIVEN
        String username = "callMe";
        String password = "password";
        String wrongPassword = "notPassword";

        UserRecord record = new UserRecord();
        record.setUsername(username);
        record.setPassword(password);
        record.setFirstName("jenny");
        record.setMiddleName("");
        record.setLastName("tutone");
        record.setPhoneNumber("867-5309");
        record.setPrimaryEmail("youveGotMyEmail@gmail.com");

        when(userRepository.findById(username)).thenReturn(Optional.of(record));

        //WHEN THEN
        Assertions.assertThrows(UsernameOrPasswordIncorrect.class,
                () -> userService.loginUser(username, wrongPassword),
                "incorrect password, should have throw error");
        verify(userRepository, times(1)).findById(username);
    }
}