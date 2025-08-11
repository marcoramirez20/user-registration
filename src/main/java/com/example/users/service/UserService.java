package com.example.users.service;

import com.example.users.dto.UserRegistrationRequest;
import com.example.users.dto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserResponse register(UserRegistrationRequest req){

        UserResponse resp = new UserResponse();
        return resp;
    }
}
