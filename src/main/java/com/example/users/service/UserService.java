package com.example.users.service;

import com.example.users.dto.PhoneRequest;
import com.example.users.dto.PhoneResponse;
import com.example.users.dto.UserRegistrationRequest;
import com.example.users.dto.UserResponse;
import com.example.users.entity.PhoneEntity;
import com.example.users.entity.UserEntity;
import com.example.users.repository.UserRepository;
import com.example.users.util.JWTGenerator;
import com.example.users.util.ValidationProperties;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ValidationProperties validationProperties;
    private final JWTGenerator jwtGenerator;

    public UserService(UserRepository userRepository,
                       ValidationProperties validationProperties,
                       JWTGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.validationProperties = validationProperties;
        this.jwtGenerator = jwtGenerator;
    }

    @Transactional
    public UserResponse createUser(UserRegistrationRequest req){
        if(userRepository.existsByEmail(req.getEmail())){
            throw new IllegalArgumentException("Correo ya registrado");
        }
        if(!req.getEmail().matches(validationProperties.getEmailRegex())) {
            throw new IllegalArgumentException("Formato del correo es inválido");
        }
        if(!req.getPassword().matches(validationProperties.getPasswordRegex())) {
            throw new IllegalArgumentException("Formato de la clave es inválido");
        }

        UserEntity entity = new UserEntity();
        entity.setName(req.getName());
        entity.setEmail(req.getEmail());
        entity.setPassword(req.getPassword());
        entity.setLastLogin(Instant.now());
        entity.setActive(true);

        if(req.getPhones() != null && !req.getPhones().isEmpty()) {
            for(PhoneRequest p : req.getPhones()) {
                PhoneEntity pe = new PhoneEntity();
                pe.setNumber(p.getNumber());
                pe.setCitycode(p.getCitycode());
                pe.setCountrycode(p.getCountrycode());
                entity.addPhone(pe);
            }
        }

        String token = jwtGenerator.generateToken(entity.getEmail());
        entity.setToken(token);

        entity = userRepository.save(entity);

        UserResponse resp = new UserResponse();
        resp.setId(entity.getId());
        resp.setCreated(entity.getCreated());
        resp.setModified(entity.getModified());
        resp.setLast_login(entity.getLastLogin());
        resp.setToken(entity.getToken());
        resp.setIsactive(entity.isActive());
        resp.setName(entity.getName());
        resp.setEmail(entity.getEmail());
        List<PhoneResponse> phones = entity.getPhones().stream().map(
                ph -> new PhoneResponse(ph.getNumber(), ph.getCitycode(), ph.getCountrycode()))
                .toList();
        resp.setPhones(phones);
        return resp;
    }
}
