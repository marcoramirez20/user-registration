package com.example.users.controller;

import com.example.users.dto.PhoneRequest;
import com.example.users.dto.UserRegistrationRequest;
import com.example.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnCreatedOnValidPayload() throws Exception {
        UserRegistrationRequest req = new UserRegistrationRequest();
        req.setName("Juan Rodriguez");
        req.setEmail("juan@rodriguez.org");
        req.setPassword("Password1");
        PhoneRequest p = new PhoneRequest();
        p.setNumber("1234567");
        p.setCitycode("1");
        p.setCountrycode("57");
        req.getPhones().add(p);

        Mockito.when(userService.createUser(Mockito.any())).thenAnswer(inv -> null);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());
    }
}
