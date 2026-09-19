package com.backend.assessment.controller;

import com.backend.assessment.dto.LoginRequest;
import com.backend.assessment.dto.LoginResponse;
import com.backend.assessment.dto.RegisterRequest;
import com.backend.assessment.dto.UserResponse;
import com.backend.assessment.service.AuthService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(
	    name = "Authentication",
	    description = "User registration and JWT login APIs"
	)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(
    	    summary = "Register a new user",
    	    description = "Creates a new user account with a securely hashed password."
    	)
    	@PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @Valid @RequestBody RegisterRequest request
    ) {

        UserResponse response =
                authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @Operation(
    	    summary = "Login",
    	    description = "Authenticates the user and returns a JWT token."
    	)
    	@PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request
    ) {

        LoginResponse response =
                authService.login(request);

        return ResponseEntity.ok(response);
    }
}