package com.backend.assessment.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/member")
@Tag(
	    name = "Member",
	    description = "Member-only APIs"
	)
public class MemberController {

	@Operation(
		    summary = "Member test endpoint",
		    description = "Accessible only to users with MEMBER role."
		)
    @GetMapping("/test")
    public String memberTest(Authentication authentication) {
        return "Welcome MEMBER: " + authentication.getName();
    }
}