package com.backend.assessment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/intern")
@Tag(
	    name = "Intern",
	    description = "Intern-only APIs"
	) 
public class InternController {

	@Operation(
		    summary = "Intern test endpoint",
		    description = "Accessible only to users with INTERN role."
		)
    @GetMapping("/test")
    public String internTest(Authentication authentication) {
        return "Welcome INTERN: " + authentication.getName();
    }
}