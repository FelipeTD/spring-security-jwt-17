package com.tortora.ti.spring.security.jwt.service;

import com.tortora.ti.spring.security.jwt.dto.RequestResponse;
import com.tortora.ti.spring.security.jwt.entity.Users;
import com.tortora.ti.spring.security.jwt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    private final UserRepository repository;
    private final JWTUtils utils;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;

    public AuthService(UserRepository repository, JWTUtils utils, PasswordEncoder encoder, AuthenticationManager manager) {
        this.repository = repository;
        this.utils = utils;
        this.encoder = encoder;
        this.manager = manager;
    }

    public RequestResponse signUp(RequestResponse registrationRequest) {
        RequestResponse response = new RequestResponse();

        try {
            Users users = new Users();
            users.setEmail(registrationRequest.getEmail());
            users.setPassword(encoder.encode(registrationRequest.getPassword()));
            users.setRole(registrationRequest.getRole());

            Users result = repository.save(users);

            if (result.getId() > 0) {
                response.setUsers(result);
                response.setMessage("User saved successfully");
                response.setStatusCode(200);
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }

    public RequestResponse signIn(RequestResponse signInRequest) {
        RequestResponse response = new RequestResponse();

        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(
                    signInRequest.getEmail(),
                    signInRequest.getPassword())
            );

            var user = repository.findByEmail(signInRequest.getEmail()).orElseThrow();
            System.out.println("USER IS: " + user);

            var jwt = utils.generateToken(user);
            var refreshToken = utils.generateRefreshToken(new HashMap<>(), user);

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }

        return response;
    }

    public RequestResponse refreshToken(RequestResponse refreshTokenRequest) {
        RequestResponse response = new RequestResponse();

        String email = utils.extractUsername(refreshTokenRequest.getToken());
        Users users = repository.findByEmail(email).orElseThrow();

        if (utils.isTokenValid(refreshTokenRequest.getToken(), users)) {
            var jwt = utils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenRequest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        } else {
            response.setStatusCode(500);
            response.setMessage("Error Refresing Token");
        }

        return response;
    }

}
