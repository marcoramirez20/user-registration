package com.example.users.service;

import com.example.users.dto.UserRegistrationRequest;
import com.example.users.repository.UserRepository;
import com.example.users.util.JWTService;
import com.example.users.util.ValidationProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        ValidationProperties validationProperties = new ValidationProperties();
        validationProperties.setEmailRegex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        validationProperties.setPasswordRegex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$");
        JWTService jwtService = Mockito.mock(JWTService.class);
        Mockito.when(jwtService.generateToken(Mockito.anyString())).thenReturn("token");
        userService = new UserService(userRepository, validationProperties, jwtService);
    }

    @Test
    void shouldRejectDuplicateEmail() {
        Mockito.when(userRepository.existsByEmail("a@a.com")).thenReturn(true);
        UserRegistrationRequest req = new UserRegistrationRequest();
        req.setName("A");
        req.setEmail("a@a.com");
        req.setPassword("Password1");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> userService.createUser(req));
        assertEquals("Correo ya registrado", ex.getMessage());
    }
}
