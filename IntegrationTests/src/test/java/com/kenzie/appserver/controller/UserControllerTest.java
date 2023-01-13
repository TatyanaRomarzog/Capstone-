package com.kenzie.appserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.appserver.IntegrationTest;
import com.kenzie.appserver.service.ApplicationService;
import com.kenzie.appserver.service.UserService;
import net.andreinc.mockneat.MockNeat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

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
        String title = mockNeat.strings().valStr();
        String author = mockNeat.strings().valStr();
        String description = mockNeat.strings().valStr();
        String location = mockNeat.strings().valStr();
        String timeDate = mockNeat.strings().valStr();
        List<String> potentialSuspects = new ArrayList<>();
    }
}
