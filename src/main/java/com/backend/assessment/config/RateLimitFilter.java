package com.backend.assessment.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createBucket() {

        Refill refill = Refill.intervally(
                10,
                Duration.ofMinutes(1)
        );

        Bandwidth limit = Bandwidth.classic(
                10,
                refill
        );

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String requestUri = request.getRequestURI();

        // Rate limit only authentication endpoints
        if (!requestUri.equals("/api/auth/login")
                && !requestUri.equals("/api/auth/register")) {

            filterChain.doFilter(request, response);
            return;
        }

        String clientIp = request.getRemoteAddr();

        // Separate rate-limit bucket for each IP + endpoint
        String bucketKey = clientIp + ":" + requestUri;

        Bucket bucket = buckets.computeIfAbsent(
                bucketKey,
                key -> createBucket()
        );

        if (bucket.tryConsume(1)) {

            filterChain.doFilter(request, response);

        } else {

            response.setStatus(429);
            response.setContentType("application/json");

            response.getWriter().write(
                    "{\"status\":429,\"message\":\"Too many requests. Please try again later.\"}"
            );
        }
    }
}