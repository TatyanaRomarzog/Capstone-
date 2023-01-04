package com.kenzie.appserver.controller;

import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.controller.model.UserLoginRequest;
import com.kenzie.appserver.controller.model.UserResponse;
import com.kenzie.appserver.controller.model.UserUpdateRequest;
import com.kenzie.appserver.service.UserService;
import com.kenzie.appserver.service.exceptions.UsernameAlreadyTaken;
import com.kenzie.appserver.service.exceptions.UsernameOrPasswordIncorrect;
import com.kenzie.appserver.service.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest createRequest) {
        User user = new User(createRequest.getUsername(),
                createRequest.getPassword(),
                createRequest.getFirstName(),
                createRequest.getMiddleName(),
                createRequest.getLastName(),
                createRequest.getPhoneNumber(),
                createRequest.getPrimaryEmail());

        try{
            userService.createAccount(user);

            return ResponseEntity.created(URI.create("/user/" + user.getUsername())).body(userToResponse(user));
        }
        catch (UsernameAlreadyTaken e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable("username") String username,
                                                   @RequestBody UserUpdateRequest updateRequest) {
        User user = new User(username,
                updateRequest.getNewPassword(),
                updateRequest.getFirstName(),
                updateRequest.getMiddleName(),
                updateRequest.getLastName(),
                updateRequest.getPhoneNumber(),
                updateRequest.getPrimaryEmail());

        try{
            userService.updateAccount(user, updateRequest.getOldPassword());

            return ResponseEntity.ok(userToResponse(user));
        }
        catch (UsernameOrPasswordIncorrect e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<UserResponse> loginUser(@RequestBody UserLoginRequest loginRequest) {
        try {
            User user = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(userToResponse(user));
        }
        catch (UsernameOrPasswordIncorrect e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private UserResponse userToResponse(User user) {
        UserResponse response = new UserResponse();

        response.setUsername(user.getUsername());
        response.setFirstName(user.getFirstName());
        response.setMiddleName(user.getMiddleName());
        response.setLastName(user.getLastName());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setPrimaryEmail(user.getPrimaryEmail());

        return response;
    }
}
