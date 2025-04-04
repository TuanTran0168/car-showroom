package com.tuantran.CarShowroom.controllers;

import com.tuantran.CarShowroom.payload.request.authentication.AuthRequest;
import com.tuantran.CarShowroom.payload.response.authentication.AuthResponse;
import com.tuantran.CarShowroom.service.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 🔹 This endpoint is not secure
     */
    @GetMapping("/welcome")
    public String welcome() {
        String welcome = "Welcome! This endpoint is secure.\n" +
                "Current Date and Time: " + LocalDateTime.now() + "\n" +
                "Author: Tuan Tran";
        return welcome;
    }

    /**
     * 🔹 Get token to access other endpoints
     */
    @PostMapping("/token")
    public ResponseEntity<AuthResponse> authenticateAndGetToken(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(authRequest.getUsername());
            AuthResponse authResponse = AuthResponse.builder()
                    .authenticated(authentication.isAuthenticated())
                    .token(token)
                    .build();
            return ResponseEntity.ok(authResponse);
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}
