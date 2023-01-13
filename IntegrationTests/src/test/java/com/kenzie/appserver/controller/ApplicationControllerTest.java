package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.controller.model.ApplicationCreateRequest;
import com.kenzie.appserver.service.ApplicationService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class ApplicationControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    ApplicationService applicationService;

    private final MockNeat mockNeat = MockNeat.threadLocal();

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void createApplication_CreateSuccessful() throws Exception{
        // GIVEN
        String firstName = mockNeat.strings().valStr();
        String lastName = mockNeat.strings().valStr();
        String homeAddress = mockNeat.strings().valStr();
        String phoneNumber = mockNeat.strings().valStr();
        String emailAddress = mockNeat.strings().valStr();
        String objective = mockNeat.strings().valStr();
        String education = mockNeat.strings().valStr();
        String experience = mockNeat.strings().valStr();
        String skills = mockNeat.strings().valStr();
        List<String> workHistory = new ArrayList<>();
        List<String> references  = new ArrayList<>();

        ApplicationCreateRequest applicationCreateRequest = new ApplicationCreateRequest();
        applicationCreateRequest.setFirstName(firstName);
        applicationCreateRequest.setLastName(lastName);
        applicationCreateRequest.setHomeAddress(homeAddress);
        applicationCreateRequest.setPhoneNumber(phoneNumber);
        applicationCreateRequest.setEmailAddress(emailAddress);
        applicationCreateRequest.setObjective(objective);
        applicationCreateRequest.setEducation(education);
        applicationCreateRequest.setExperience(experience);
        applicationCreateRequest.setSkills(skills);
        applicationCreateRequest.setWorkHistory(workHistory);
        applicationCreateRequest.setReferences(references);

        mapper.registerModule(new JavaTimeModule());

        // WHEN
        mvc.perform(post("/applications")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(applicationCreateRequest)))
                //THEN
        .andExpect(jsonPath("firstName")
                .value(is(firstName)))
                .andExpect(jsonPath("lastName")
                        .value(is(lastName)))
                .andExpect(jsonPath("homeAddress")
                        .value(is(homeAddress)))
                .andExpect(jsonPath("phoneNumber")
                        .value(is(phoneNumber)))
                .andExpect(jsonPath("emailAddress")
                        .value(is(emailAddress)))
                .andExpect(jsonPath("objective")
                        .value(is(objective)))
                .andExpect(jsonPath("education")
                        .value(is(education)))
                .andExpect(jsonPath("experience")
                        .value(is(experience)))
                .andExpect(jsonPath("skills")
                        .value(is(skills)))
                .andExpect(jsonPath("workHistory")
                        .value(is(workHistory)))
                .andExpect(jsonPath("references")
                        .value(is(references)))
                .andExpect(status().isCreated());
    }
}
