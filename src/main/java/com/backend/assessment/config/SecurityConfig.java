package com.backend.assessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	private final RateLimitFilter rateLimitFilter;
    
	public SecurityConfig(
	        JwtAuthenticationFilter jwtAuthenticationFilter,
	        RateLimitFilter rateLimitFilter
	) {
	    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	    this.rateLimitFilter = rateLimitFilter;
	}

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            .authorizeHttpRequests(auth -> auth

            	    // Swagger
            	    .requestMatchers(
            	        "/swagger-ui/**",
            	        "/swagger-ui.html",
            	        "/v3/api-docs/**"
            	    ).permitAll()

            	    // Public APIs
            	    .requestMatchers(
            	        "/api/auth/register",
            	        "/api/auth/login"
            	    ).permitAll()

                // ADMIN only
                .requestMatchers("/api/admin/**")
                .hasRole("ADMIN")

                // INTERN only
                .requestMatchers("/api/intern/**")
                .hasRole("INTERN")

                // MEMBER only
                .requestMatchers("/api/member/**")
                .hasRole("MEMBER")

                // Any other API requires authentication
                .anyRequest().authenticated()
            )

            .addFilterBefore(
            	    jwtAuthenticationFilter,
            	    UsernamePasswordAuthenticationFilter.class
            	)

            	.addFilterBefore(
            	    rateLimitFilter,
            	    JwtAuthenticationFilter.class
            	);

        return http.build();
    }
}