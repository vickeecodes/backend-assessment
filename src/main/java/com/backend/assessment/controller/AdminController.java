package com.backend.assessment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/admin")
@Tag(
	    name = "Admin",
	    description = "Admin-only APIs"
	)
public class AdminController {

	@Operation(
		    summary = "Admin test endpoint",
		    description = "Accessible only to users with ADMIN role."
		)
    @GetMapping("/test")
    public String adminTest(Authentication authentication) {

        return "Welcome ADMIN: " + authentication.getName();
    }
}