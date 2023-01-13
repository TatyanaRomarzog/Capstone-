package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.UserCreateRequest;
import com.kenzie.appserver.service.ApplicationService;
import com.kenzie.appserver.service.UserService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@IntegrationTest
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    UserService userService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createUser_CreateSuccessful() throws Exception{
        // GIVEN
        String username = mockNeat.strings().valStr();
        String password = mockNeat.strings().valStr();
        String firstName = mockNeat.strings().valStr();
        String middleName = mockNeat.strings().valStr();
        String lastName = mockNeat.strings().valStr();
        String phoneNumber = mockNeat.strings().valStr();
        String primaryEmail = mockNeat.strings().valStr();

        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername(username);
        userCreateRequest.setPassword(password);
        userCreateRequest.setFirstName(firstName);
        userCreateRequest.setMiddleName(middleName);
        userCreateRequest.setLastName(lastName);
        userCreateRequest.setPhoneNumber(phoneNumber);
        userCreateRequest.setPrimaryEmail(primaryEmail);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(userCreateRequest)))
                //THEN
        .andExpect(jsonPath("username")
                .value(is(username)))
                .andExpect(jsonPath("password")
                        .value(is(password)))
                .andExpect(jsonPath("firstName")
                        .value(is(firstName)))
                .andExpect(jsonPath("middleName")
                        .value(is(middleName)))
                .andExpect(jsonPath("lastName")
                        .value(is(lastName)))
                .andExpect(jsonPath("phoneNumber")
                        .value(is(phoneNumber)))
                .andExpect(jsonPath("primaryEmail")
                        .value(is(primaryEmail)));
    }
}
