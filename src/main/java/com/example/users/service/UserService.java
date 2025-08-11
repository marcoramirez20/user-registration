package com.example.users.service;

import com.example.users.dto.UserRegistrationRequest;
import com.example.users.dto.UserResponse;
import com.example.users.repository.UserRepository;
import com.example.users.util.JwtGenerator;
import com.example.users.util.ValidationProperties;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    //TODO

    private final UserRepository userRepository;
    private final ValidationProperties validationProperties;
    private final JwtGenerator jwtGenerator;

    public UserService(UserRepository userRepository,
                       ValidationProperties validationProperties,
                       JwtGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.validationProperties = validationProperties;
        this.jwtGenerator = jwtGenerator;
    }

    public UserResponse register(UserRegistrationRequest req){

        UserResponse resp = new UserResponse();
        return resp;
    }
}
